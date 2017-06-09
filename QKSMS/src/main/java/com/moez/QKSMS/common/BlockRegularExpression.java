package com.moez.QKSMS.common;

/**
 * Created by imlinux on 2017/5/28.
 */
import android.content.SharedPreferences;

//import com.moez.QKSMS.common.utils.PhoneNumberUtils;
import com.moez.QKSMS.ui.settings.SettingsFragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockRegularExpression {

    public static void blockFutureConversation(SharedPreferences prefs, String address, String text) {
        Set<String> idStrings = prefs.getStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, new HashSet<String>());
        JSONArray mrule = new JSONArray();
        mrule.put(address);
        mrule.put(text);
        idStrings.add(mrule.toString());
        prefs.edit().putStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, idStrings).apply();
    }

    public static void unblockFutureConversation(SharedPreferences prefs, String address) {
        Set<String> idStrings2 = prefs.getStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, new HashSet<String>());
        idStrings2.remove(address);
        prefs.edit().putStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, idStrings2).apply();
    }

    public static Set<String> getFutureBlockedConversations(SharedPreferences prefs) {
        return prefs.getStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, new HashSet<String>());
    }

    public static boolean isFutureBlocked(SharedPreferences prefs, String address, String text) {
        for (String s : getFutureBlockedConversations(prefs)) {
            String saddress,stext;
            try {
                JSONArray jA = new JSONArray(s);
                saddress = jA.getString(0);
                stext = jA.getString(1);
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
            if (stext == "" && stext == "") {
                if (Pattern.matches(saddress, address) && Pattern.matches(stext,text)) {
                    return true;
                }
            }
            else
                if (Pattern.matches(saddress,address) || Pattern.matches(stext,text)) {
                    return true;
            }
        }

        return false;
    }

}
