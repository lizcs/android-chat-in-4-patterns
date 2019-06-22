package nju.androidchat.client.hw1;

import android.widget.ImageView;

import java.util.List;

import nju.androidchat.client.ClientMessage;

public interface Hw1Contract {
    interface View extends BaseView<Presenter> {
        void showMessageList(List<ClientMessage> messages);
    }

    interface Presenter extends BasePresenter {
        void sendMessage(String content);

        void receiveMessage(ClientMessage content);

        String getUsername();

        //撤回消息mvp0不实现
        void recallMessage(int index0);

        void loadImage(ImageView imageView, String path);
    }

    interface Model {
        ClientMessage sendInformation(String message);

        String getUsername();
    }
}