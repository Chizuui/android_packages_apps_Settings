/*
 * Copyright (C) 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;

import com.android.settings.core.BasePreferenceController;

public class BackgroundBlurPreferenceController extends BasePreferenceController
        implements Preference.OnPreferenceChangeListener {

    private static final String DISABLE_WINDOW_BLURS = "disable_window_blurs";

    public BackgroundBlurPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);

        if (preference instanceof TwoStatePreference) {
            final boolean enabled = Settings.Global.getInt(
                    mContext.getContentResolver(),
                    DISABLE_WINDOW_BLURS,
                    0) == 0;

            ((TwoStatePreference) preference).setChecked(enabled);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final boolean enabled = (Boolean) newValue;

        // disable_window_blurs:
        // 0 = blur enabled
        // 1 = blur disabled
        return Settings.Global.putInt(
                mContext.getContentResolver(),
                DISABLE_WINDOW_BLURS,
                enabled ? 0 : 1);
    }
}
