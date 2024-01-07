package ru.vsokolova.volumetric_table

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//import ru.vsokolova.volumetric_table.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class DensityFragmentTest {

//    @get:Rule val activityRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule val disableAnimationRule = DisableAnimationsRule()

    @Before
    fun setUp() {
        onView(withText("Плотность")).perform(click())
    }

    @Test
    fun checkSettingWoodType(){
        onView(withId(R.id.spinner_wood_type)).perform(click())
        onData(anything()).atPosition(2).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.spinner_wood_type)).check(matches(withText("Бук")))
    }

    @Test
    fun checkSettingHumidity(){
        onView(withId(R.id.spinner_humidity_value)).perform(click())
        onData(anything()).atPosition(5).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.spinner_humidity_value)).check(matches(withText("40")))
    }

    @Test
    fun checkSettingDensity(){
        onView(withId(R.id.spinner_wood_type)).perform(click())
        onData(anything()).atPosition(4).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.spinner_humidity_value)).perform(click())
        onData(anything()).atPosition(3).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.text_view_result)).check(matches(withText("740 кг/м3")))
    }
}