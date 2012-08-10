package com.madgag.android.util.store;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;
import static com.madgag.agit.R.string.button_cancel;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.madgag.agit.R;


public class InstallAppDialogFragment extends DialogFragment {

    private static final String ICON = "icon", TITLE = "title", MESSAGE = "message", APP_ID = "appId";

    public static InstallAppDialogFragment newInstance(int icon, int title, int message, String appId) {
        InstallAppDialogFragment f = new InstallAppDialogFragment();

        Bundle args = new Bundle();
        args.putInt(ICON, icon);
        args.putInt(TITLE, title);
        args.putInt(MESSAGE, message);
        args.putString(APP_ID, appId);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        FragmentActivity activity = getActivity();
        return new AlertDialog.Builder(activity)
                .setIcon(args.getInt(ICON))
                .setTitle(args.getInt(TITLE))
                .setMessage(args.getInt(MESSAGE))
                .setPositiveButton(activity.getString(R.string.market_button),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                startActivity(marketDownloadIntentFor(args.getString(APP_ID)));
                            }
                        }
                ).setNegativeButton(button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .create();
    }


    private static final String MARKET_PACKAGE_DETAILS_PREFIX = "market://details?id=";

    private static Intent marketDownloadIntentFor(String appPackageId) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_PACKAGE_DETAILS_PREFIX + appPackageId));
    }

    /**
     * Indicates whether the specified action can be used as an intent.
     * <p/>
     * Adapted from http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
     */
    public static boolean isIntentAvailable(Context context, String action) {
        return isIntentAvailable(context, new Intent(action));
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, MATCH_DEFAULT_ONLY).isEmpty();
    }

}
