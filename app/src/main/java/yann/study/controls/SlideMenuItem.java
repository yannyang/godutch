package yann.study.controls;

/**
 * Created by yann on 2015/5/15.
 * 菜单实体类
 */
public class SlideMenuItem {
    private int mItemId;
    private String mTitle;

    public SlideMenuItem(int pItemId, String pTitle) {
        this.mItemId = pItemId;
        this.mTitle = pTitle;
    }

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int pItemId) {
        this.mItemId = pItemId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String pTitle) {
        this.mTitle = pTitle;
    }
}
