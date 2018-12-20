package cc.bw.com.myeleme.bean;

import java.util.List;

import cc.bw.com.myeleme.R;

public class Shop {
        /**
         * list : []
         * sellerName :
         * sellerid : 0
         */


        int textColor = 0xffffffff;
        int background = R.color.grayblack;
        private String sellerName;
        private String sellerid;
        private List<Goods> list;
        private boolean check;

    public void setBackground(int background) {
        this.background = background;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackground() {
        return background;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<Goods> getList() {
            return list;
        }

        public void setList(List<Goods> list) {
            this.list = list;
        }
}
