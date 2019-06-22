package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.util.UUID;

import nju.androidchat.client.R;
import nju.androidchat.client.hw1.MyImageView;

public class ItemTextReceive extends LinearLayout {


    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;

    private MyImageView myImageView;
    private String str;


    public ItemTextReceive(Context context, String text, UUID messageId) {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_text_receive, this);
        this.textView = findViewById(R.id.chat_item_content_text);
        this.messageId = messageId;
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
    }

    public void init(Context context) {

    }

    public String getText() {
        return this.str;
    }

    public void setText(String text) {
        this.str = text;
        textView.setText(text);
    }
}
