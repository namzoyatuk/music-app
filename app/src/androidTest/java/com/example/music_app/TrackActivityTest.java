//package com.example.music_app;
//
//import androidx.test.core.app.ActivityScenario;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.ext.truth.content.IntentSubject;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//
//import com.example.music_app.ui.TrackActivity;
//import com.example.music_app.viewmodel.TrackViewModel;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.mockito.Mockito.when;
//
//
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//public class TrackActivityTest {
//
//    @Rule
//    public ActivityScenarioRule<TrackActivity> activityRule = new ActivityScenarioRule<>(TrackActivity.class);
//
//    @Mock
//    private TrackViewModel trackViewModel;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testTrackActivityUI() {
//        // Mock the ViewModel to provide test data
//        when(trackViewModel.getTrack("6G18KE6mtFITAOCFxGjk7P")).thenReturn(/* Provide test LiveData here */);
//
//        // Verify that the UI elements are displayed
//        onView(withId(R.id.track_recycler_view)).check(matches(isDisplayed()));
//        onView(withId(R.id.track_image)).check(matches(isDisplayed()));
//        onView(withId(R.id.artist_name)).check(matches(isDisplayed()));
//    }
//
//}
