package com.example.shopmall.shopmall1710a.register;

public class RegisterContract {

    public interface IRegisterPresenter {
        void registe(String name, String password);
    }

    public interface IRegisteView {
        void onRegisteSuccess();
        void onRegisteFailed();
    }
}
