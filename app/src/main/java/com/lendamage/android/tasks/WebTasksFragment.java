package com.lendamage.android.tasks;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WebView webView = (WebView) super.onCreateView(inflater, container, savedInstanceState);

        webView.setWebViewClient(new TasksWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://gmail.com/tasks");

        return webView;
    }

    static class TasksWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //http://stackoverflow.com/questions/4066438/android-webview-how-to-handle-redirects-in-app-instead-of-opening-a-browser
            view.loadUrl(url);
            return false;
        }

    }


}
