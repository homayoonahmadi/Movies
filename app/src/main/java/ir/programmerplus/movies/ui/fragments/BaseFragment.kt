package ir.programmerplus.movies.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ir.programmerplus.movies.utils.getWindowBackgroundColor
import java.util.concurrent.TimeUnit


abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // we don't need to change transition animations when method calls again!
        if (savedInstanceState != null) return

        // here we pause enter transition animation to load view completely
        postponeEnterTransition()

        // we set the background color of root view to white
        // because navigation animations run on a transparent background by default
        context?.let { context ->
            view.setBackgroundColor(context.getWindowBackgroundColor())
        }

        // here we start transition using a handler
        // to make sure transition animation won't be lagged
        view.post { postponeEnterTransition(0, TimeUnit.MILLISECONDS) }
    }

}