package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.util.UUID;

import lombok.Setter;
import nju.androidchat.client.R;
import nju.androidchat.client.hw1.MyImageView;

public class ItemTextSend extends LinearLayout implements View.OnLongClickListener {
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private Context context;
    private UUID messageId;
    @Setter private OnRecallMessageRequested onRecallMessageRequested;

    private MyImageView myImageView;
    private String str;

    public ItemTextSend(Context context, String text, UUID messageId, OnRecallMessageRequested onRecallMessageRequested) {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_text_send, this);
        this.textView = findViewById(R.id.chat_item_content_text);
        this.myImageView = findViewById(R.id.chat_item_content_img);
        this.messageId = messageId;
        this.onRecallMessageRequested = onRecallMessageRequested;

        this.str = text;
        if(text.startsWith("![]({") && text.endsWith("})")){
            String urlString = text.substring(5, text.length() - 2);
            myImageView.setImageURL(urlString);
            this.myImageView.setVisibility(VISIBLE);
            this.textView.setVisibility(INVISIBLE);
        }
        else {
            this.myImageView.setVisibility(INVISIBLE);
            this.textView.setVisibility(VISIBLE);
            setText(text);
        }

        this.setOnLongClickListener(this);
    }

    public String getText() {
        return this.str;
    }

    public void setText(String text) {
        this.str = text;
        textView.setText(text);
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要撤回这条消息吗？")
                .setPositiveButton("是", (dialog, which) -> {
                    if (onRecallMessageRequested != null) {
                        onRecallMessageRequested.onRecallMessageRequested(this.messageId);
                    }
                })
                .setNegativeButton("否", ((dialog, which) -> {
                }))
                .create()
                .show();

        return true;


    }

}
