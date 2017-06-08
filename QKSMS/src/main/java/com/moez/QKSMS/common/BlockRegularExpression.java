package com.moez.QKSMS.common;

/**
 * Created by imlinux on 2017/5/28.
 */
import android.content.SharedPreferences;

//import com.moez.QKSMS.common.utils.PhoneNumberUtils;
import com.moez.QKSMS.ui.settings.SettingsFragment;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockRegularExpression {
/*    private static final BlockRegularExpression ourInstance = new BlockRegularExpression();
//    private string mpattern;

    static BlockRegularExpression getInstance() {
        return ourInstance;
    }

    private BlockRegularExpression() {
    }*/

    public static void blockFutureConversation(SharedPreferences prefs, String address) {
        Set<String> idStrings = prefs.getStringSet(SettingsFragment.BLOCKED_FUTURE_TEXT, new HashSet<String>());
        idStrings.add(address);
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

    public static boolean isFutureBlocked(SharedPreferences prefs, String address) {
        for (String s : getFutureBlockedConversations(prefs)) {
            if (Pattern.matches(s, address)) {
                return true;
            }
        }

        return false;
    }

}
