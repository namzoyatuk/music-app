package com.example.music_app;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.music_app.adapter.MainAlbumAdapter;
import com.example.music_app.databinding.ActivityMainBinding;
import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.SearchResponseDto;
import com.example.music_app.repository.AlbumRepository;
import com.example.music_app.repository.SearchRepository;
import com.example.music_app.ui.SearchFragment;
import com.example.music_app.viewmodel.AlbumViewModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumViewModel albumViewModel;

    @Mock
    private ViewModelProvider.Factory viewModelFactory;

    @Mock
    private RecyclerView recyclerViewAlbums;

    @Mock
    private GridLayoutManager gridLayoutManager;

    @Mock
    private MainAlbumAdapter mainAlbumAdapter;

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private FragmentTransaction fragmentTransaction;

    private ActivityMainBinding binding;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        activityScenarioRule.getScenario().onActivity(activity -> {
            try {
                Field bindingField = MainActivity.class.getDeclaredField("binding");
                bindingField.setAccessible(true);
                binding = (ActivityMainBinding) bindingField.get(activity);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testOnCreate() {
        // Launch the activity
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();

        // Verify interactions
        scenario.onActivity(activity -> {
            assertNotNull(binding.getRoot());
            assertNotNull(binding.recyclerViewAlbums.getLayoutManager());
            assertNotNull(binding.recyclerViewAlbums.getAdapter());
        });
    }

    @Test
    public void testSearchSpotify() {
        // Mock the behavior of SearchRepository
        MutableLiveData<SearchResponseDto> liveData = new MutableLiveData<>();
        SearchResponseDto searchResponseDto = mock(SearchResponseDto.class);
        when(searchRepository.search(anyString(), anyString())).thenReturn(liveData);

        // Launch the activity and perform search
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.onActivity(activity -> {
            activity.searchSpotify("test query");
            activity.runOnUiThread(() -> liveData.setValue(searchResponseDto));
        });

        // Verify interactions
        scenario.onActivity(activity -> {
            FragmentManager fragmentManager = mock(FragmentManager.class); // Mock FragmentManager
            FragmentTransaction fragmentTransaction = mock(FragmentTransaction.class);
            when(fragmentTransaction.replace(eq(R.id.fragment_container), any(SearchFragment.class))).thenReturn(fragmentTransaction);

            activity.searchSpotify("test query");
            activity.runOnUiThread(() -> liveData.setValue(searchResponseDto));

        });
    }
}