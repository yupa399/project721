package ais.app.apar.entity;

import java.io.Serializable;

public class MainContent implements Serializable {

    private int _id;
    private String _title;
    private String _content;

    public MainContent() {

    }

    public MainContent(int _id, String _title, String _content) {
        this.set_title(_title);
        this.set_content(_content);
        this.set_id(_id);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}
