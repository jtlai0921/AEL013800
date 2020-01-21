package workshop.mylistview;
//清單資料類別
public class Item {
    private int imgId;			// 課程圖示代碼
    private String lesson;		// 課程名稱
    private String comment;		// 課程說明

      public int getimgId() {
          return imgId;
      }
      public void setimgId(int imgId) {
          this.imgId = imgId;
      }
      public String getlesson() {
          return lesson;
      }
      public void setlesson(String lesson) {
          this.lesson = lesson;
      }
      public String getcomment() {
          return comment;
      }
      public void setcomment(String comment) {
          this.comment = comment;
      }
}
