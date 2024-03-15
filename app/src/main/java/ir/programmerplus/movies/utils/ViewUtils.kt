package ir.programmerplus.movies.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mikelau.views.shimmer.ShimmerRecyclerViewX
import ir.programmerplus.movies.R

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * Shows a snackBar with the given message and an optional action to retry

 * @param message the message to display in the snackBar
 * @param action an optional function to execute when the action is clicked
 */
fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        action?.let {
            setAction(context.getString(R.string.retry)) { action.invoke() }
        }
        show()
    }
}

/**
 * Retrieves the background color of the current window.
 *
 * @return the background color of the current window as an integer color value.
 */
fun Context.getWindowBackgroundColor(): Int {
    val typedValue = TypedValue()

    // Retrieve the resolved color value from the current theme
    theme?.resolveAttribute(android.R.attr.windowBackground, typedValue, true)

    // Check if the resolved value is a color
    if (typedValue.type in TypedValue.TYPE_INT_COLOR_ARGB8..TypedValue.TYPE_INT_COLOR_RGB8) {
        return typedValue.data
    }

    // return default color if we couldn't get windowBackground color
    return if (resources.isInDarkMode()) Color.BLACK else Color.WHITE
}


/**
 * Checks if the current context is in dark mode.
 *
 * @return true if the current context is in dark mode, false otherwise.
 */
fun Resources.isInDarkMode(): Boolean {
    val currentNightMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}

/**
 * Displays the shimmer effect in a ShimmerRecyclerViewX by switching to the shimmer adapter.
 */
fun ShimmerRecyclerViewX.showShimmer() {
    if (adapter != shimmerAdapter) showShimmerAdapter()
}

/**
 * Hides the shimmer effect in a ShimmerRecyclerViewX by reverting to the original adapter.
 */
fun ShimmerRecyclerViewX.hideShimmer() {
    if (adapter == shimmerAdapter) hideShimmerAdapter()
}