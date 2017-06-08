package com.moez.QKSMS.ui.dialog;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.moez.QKSMS.R;
//import com.moez.QKSMS.common.BlockedConversationHelper;
import com.moez.QKSMS.common.BlockRegularExpression;
import com.moez.QKSMS.ui.base.QKActivity;
import com.moez.QKSMS.ui.view.QKEditText;

import java.util.Set;

/**
 * Created by imlinux on 2017/6/1.
 */

public class BlockMSMDialog {
    private static final BlockMSMDialog ourInstance = new BlockMSMDialog();

    public static BlockMSMDialog getInstance() {
        return ourInstance;
    }

    private BlockMSMDialog() {
    }

    public static void showDialog(final QKActivity context) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> addresses = BlockRegularExpression.getFutureBlockedConversations(prefs);

        new QKDialog()
                .setContext(context)
                .setTitle(R.string.pref_block_future_text)
                .setItems(addresses.toArray(new String[addresses.size()]), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        new QKDialog()
                                .setContext(context)
                                .setTitle(R.string.title_unblock_address)
                                .setMessage(((TextView) view).getText().toString())
                                .setPositiveButton(R.string.yes, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        BlockRegularExpression.unblockFutureConversation(prefs, ((TextView) view).getText().toString());
                                    }
                                })
                                .setNegativeButton(R.string.cancel, null)
                                .show();
                    }
                })
                .setPositiveButton(R.string.add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final QKEditText editText = new QKEditText(context);
                        new QKDialog()
                                .setContext(context)
                                .setTitle(R.string.title_block_text)
                                .setCustomView(editText)
                                .setPositiveButton(R.string.add, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (editText.getText().length() > 0) {
                                            BlockRegularExpression.blockFutureConversation(prefs, editText.getText().toString());
                                        }
                                    }
                                })
                                .setNegativeButton(R.string.cancel, null)
                                .show();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
