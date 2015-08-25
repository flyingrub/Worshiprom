package fly.worship.grub.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import fly.worship.grub.BuildConfig;
import fly.worship.grub.R;

/**
 * Created by Ronan on 03/07/2014.
 */
public class Settings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        final Preference acShow = findPreference("pref_key_action_show");
        acShow.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newVal) {
                createReloadAlert();
                return true;
            }

        });

        findPreference("version").setSummary(String.format("v%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void createReloadAlert() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder
                .setMessage(R.string.reload_alert)
                .setCancelable(false)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(refresh);
                                finish();
                            }
                        }
                );
        localBuilder.create().show();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(refresh);
        finish();
    }
}
