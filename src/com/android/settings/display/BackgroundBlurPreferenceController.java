package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.core.TogglePreferenceController;

public class BackgroundBlurPreferenceController extends TogglePreferenceController {

    private static final String DISABLE_WINDOW_BLURS = "disable_window_blurs";

    public BackgroundBlurPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public boolean isChecked() {
        return Settings.Global.getInt(
                mContext.getContentResolver(),
                DISABLE_WINDOW_BLURS,
                0) == 0;
    }

    @Override
    public boolean setChecked(boolean isChecked) {
        return Settings.Global.putInt(
                mContext.getContentResolver(),
                DISABLE_WINDOW_BLURS,
                isChecked ? 0 : 1);
    }

    @Override
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_display;
    }
}
