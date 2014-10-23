package com.lendamage.android.tasks;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebTasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WebTasksFragment extends WebViewFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WebTasksFragment.
     */
    public static WebTasksFragment newInstance(int sectionNumber) {
        WebTasksFragment fragment = new WebTasksFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public WebTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
        }

        //TODO
    }

}
