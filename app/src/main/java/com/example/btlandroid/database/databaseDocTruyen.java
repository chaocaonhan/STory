package com.example.btlandroid.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.btlandroid.model.TaiKhoan;
import com.example.btlandroid.model.Truyen;

public class databaseDocTruyen extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "doctruyen";

    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";

    private static int DATABASE_VERSION = 1;

    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    private Context context;

    // Biến lưu câu lệnh tạo bảng tài khoản
    private String SQLQuery = "CREATE TABLE " + TABLE_TAIKHOAN + " ( " + ID_TAI_KHOAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEN_TAI_KHOAN + " TEXT UNIQUE, " +
            MAT_KHAU + " TEXT, " +
            EMAIL + " TEXT, " +
            PHAN_QUYEN + " INTEGER)";

    // Biến lưu câu lệnh tạo bảng truyện
    private String SQLQuery1 = "CREATE TABLE " + TABLE_TRUYEN + " (" + ID_TRUYEN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEN_TRUYEN + " TEXT UNIQUE, " +
            NOI_DUNG + " TEXT, " +
            IMAGE + " TEXT, " + ID_TAI_KHOAN + " INTEGER, FOREIGN KEY (" + ID_TAI_KHOAN + ") REFERENCES " +
            TABLE_TAIKHOAN + "(" + ID_TAI_KHOAN +"))";

    // Insert dữ liệu vào bảng tài khoản, phân quyền Admin và người dùng
    private String SQLQuery2 = "INSERT INTO TaiKhoan VALUES (null, 'admin', 'admin', 'admin@gmail.com', 2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VALUES (null, 'khach', 'khach', 'khach@gmail.com', 2)";

    // Insert truyện
    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Ánh Nắng Sớm','Gà gáy thì trời sáng, " +
            "gà không gáy trời vẫn sẽ sáng. Vậy nên trời sáng hay không hoàn toàn không phải do gà gáy quyết định, mà quan trọng là người có tỉnh giấc hay không."+
            "Khi người thức dậy là một ngày cũ đã qua, nếu không thức dậy thì sẽ ngủ vùi cả một đời người."+
            "\n\nBài học:\n"+
            "Mỗi ngày thức dậy là một cuộc sống mới."+
            "Có thể sống thật khỏe mạnh là điều quan trọng nhất và hạnh phúc nhất trên đời."+
            "Tranh thủ khi ánh nắng sớm dịu nhẹ, hãy bước ra ngoài dạo ngắm cảnh vật. Cỏ cây, hoa lá, chim muông trên đời mới đẹp làm sao, đừng để khoảng thời gian tuyệt vời này trôi qua đầy lãng phí.','https://st.quantrimang.com/photos/image/2022/02/11/chuyen-ngan-y-nghia-1.jpg',1)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Vẹt và Quạ','Ngày nọ, vẹt và quạ gặp nhau, vẹt ở trong lồng có cuộc sống an nhàn, thoái mái, nhưng chú lại rất hâm mộ quạ được tự do trong tự nhiên.\n" +
            "\n" +
            "Vẹt ngưỡng mộ sự tự do của quạ, quạ lại hâm mộ cuộc sống an nhàn của vẹt, vì vậy hai con chim đã thương lượng cả hai sẽ đổi chỗ cho nhau.\n" +
            "\n" +
            "Quạ nhận được cuộc sống an nhàn mình mong muốn, nhưng chú lại không nhận được sự yêu mến của chủ nhân, và cuối cùng chết vì hậm hực;\n" +
            "\n" +
            "Vẹt có được tự do, nhưng vì đã sống trong sung sướng quá lâu nên không còn khả năng sinh tồn độc lập, cuối cùng chú ta cũng chết vì đói.\n" +
            "\n" +
            "Bài học:\n" +
            "\n" +
            "Đừng hâm mộ với hạnh phúc của người khác một cách mù quáng, vì có thể hạnh phúc ấy lại trở thành bất hạnh với bạn.\n" +
            "\n" +
            "Ở trên đời, đừng so đo thiệt hơn, sống tốt cuộc sống của chính mình, tận hưởng từng điều mà cuộc sống ban cho chúng ta chính là chân lý!','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg',1)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'Lòng khoan dung','Khi một giọt mực rơi vào cốc nước trong, cốc nước sẽ lập tức đổi màu và không uống được nữa;\n" +
            "\n" +
            "Khi một giọt mực tan vào trong biển, biển vẫn xanh thẳm như trước;\n" +
            "\n" +
            "Vì sao lại thế?\n" +
            "\n" +
            "Vì sự bao dung của hai vật thể ấy khác nhau.\n" +
            "\n" +
            "Bông lúa non thẳng thân đón gió, bông lúa càng chín càng cúi thấp đầu;\n" +
            "\n" +
            "Vì sao vậy? Bởi vì có sự khác biệt giữa trọng lượng của hai bên.\n" +
            "\n" +
            "Bài học:\n" +
            "\n" +
            "Khoan dung với người khác chính là độ lượng; khiêm tốn cúi mình chính là tự biết phân lượng; kết hợp hai điều ấy với nhau sẽ hình thành nên phẩm giá của một người.\n" +
            "\n" +
            "Tạo khuôn chứa càng lớn, lòng bao dung càng rộng mở, EQ càng cao sẽ càng hiểu rằng ta phải cúi mình.\n" +
            "\n" +
            "Hi vọng tất cả chúng ta đều có thể trở thành những người có EQ cao với lòng độ lượng bao la như biển.','https://kaylanguyen2009.weebly.com/uploads/7/7/8/6/77869878/4_orig.png',1)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Chú bé chăn cừu','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "– Sói ! Sói !\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "– “Sói ! Sói !”.\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "– “Sói ! Sói !”.\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg',1)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'Cậu bé chăn cừu và cây đa cổ thụ','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg',1)";

    public databaseDocTruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TAIKHOAN, null);
        //res.close();
        return res;
    }

    public void AddTaiKhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN, taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU, taiKhoan.getmMatKhau());
        values.put(EMAIL, taiKhoan.getmEmail());
        values.put(PHAN_QUYEN, taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);
        //db.close();
        Log.e("ADD TaiKhoan", "Successfully");
    }

    public Cursor getData1() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TRUYEN + " ORDER BY " + ID_TRUYEN + " DESC LIMIT 4", null);
        //res.close();
        return res;
    }

    public Cursor getData2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_TRUYEN, null);
        //res.close();
        return res;
    }
    public void AddTruyen(Truyen truyen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN, truyen.getTenTruyen());
        values.put(NOI_DUNG, truyen.getNoiDung());
        values.put(IMAGE, truyen.getAnh());
        values.put(ID_TAI_KHOAN, truyen.getID_TK());

        db.insert(TABLE_TRUYEN, null, values);
//        db.close();
    }

    public void UpdateTruyen(Truyen truyen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN, truyen.getTenTruyen());
        values.put(NOI_DUNG, truyen.getNoiDung());
        values.put(IMAGE, truyen.getAnh());

        Log.d("Test", "Test: " + truyen.getTenTruyen());
        db.update(TABLE_TRUYEN,  values, TEN_TRUYEN + " = ?", new String[] {truyen.getTenTruyen()});
//        db.close();
    }

    public int Delete(String tenTruyen) {
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_TRUYEN, TEN_TRUYEN + " =? ", new String[]{tenTruyen});
        db.close();
        return res;
    }
}
