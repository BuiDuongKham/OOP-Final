# OOP-Final
Bài làm của Bùi Dương Khâm lớp tín chỉ OOP JAVA N08, mã sinh viên 21011219, lớp niên chế K15-KHMT
<p>Lưu ý: Bài làm hoàn toàn trên IDE Intellij IDEA nên có thể sẽ chưa tương thích với các IDE khác. Nếu có một số lỗi cài đặt xảy ra,em xin cô (thầy) điều chỉnh giúp em. Em xin cảm ơn!</p>
<p>Cấu trúc project (chỉ tính phần src)</p>
<img src="https://github.com/BuiDuongKham/OOP-Final/blob/master/src/Instruction/Capture.PNG"/>
<ul>
    <li>File chạy chương trình chính: App.java</li>
    <li>3 File lần lượt lưu trữ thông tin của nhà tù, tù nhân, người thăm nuôi tù nhân: prison.xml, prisoner.xml, visitor.xml</li>
    <li>Package View: lưu trữ các giao diện hiển thị (4 giao diện: giao diện quản lý các nhà tù, giao diện quản lý các tù nhân, giao diện đăng kí thăm nuôi tù nhân, giao diện tổng hợp các lượt đăng kí thăm nuôi).</li>
    <li>Package Entity: lưu trữ các đối tượng (3 đối tượng: nhà tù, tù nhân và người thăm nuôi) và các class xml để đọc đối tượng đó. Ví dụ như đối tượng tù nhân có file Prison.java để tạo đối tượng và file PrisonXML.java để đọc đối tượng.</li>
    <li>Package Controller: là nơi xử lý logic chính của chương trình. Người dùng tương tác với view được điều khiển bởi Controller.</li>
    <li>Package Utils: lưu trữ các hàm tiện ích để đọc, ghi file XML</li>
    <li>Package Func: lưu trữ các đối tượng để sử dụng file Utils để đọc, ghi dữ liệu (khi sử dụng thường được đặt hậu tố Dao (Data Accessing Object) đằng sau</li>
    <li>Package Store: lưu trữ các đối tượng lưu trữ chính. Những file này sẽ chịu trách nhiệm chính, sử dụng các file Func để tương tác với Controller.</li>
<ul>    
    
## Work Flow
<h2>Khi bắt đầu chạy file App, giao diện hiện ra sẽ như sau:</h2>
<img src="https://github.com/BuiDuongKham/OOP-Final/blob/master/src/Instruction/PrisonView.PNG" />
<p> Đây là giao diện quản lý các nhà tù có các chức năng: thêm, sửa, xóa, tìm kiếm (theo tên nhà tù và ID tù), sắp xếp(theo ID, theo số tù nhân, theo tên), thống kê số lượng nhà tù, thống kê tổng số tù nhân, xem danh sách thân nhân đăng kí thăm nuôi</p>
<p> Một số quy tắc khi thêm nhà tù </p>
<ol>
   <li>ID nhà tù phải là số và không được trùng với một trong những nhà tù hiện có</li> 
    <li>Điện thoại nhà tù phải là 1 dãy số không lớn hơn 10 số</li>
</ol>
<h3>Khi chọn "Danh sách đăng kí thăm nuôi", giao diện danh sách những người đăng kí thăm nuôi sẽ hiện ra</h3>
<img src="https://github.com/BuiDuongKham/OOP-Final/blob/master/src/Instruction/ListVisitorView.PNG"/>
<h2> Ở màn hình nhà tù, chọn 1 nhà tù bất kì khi nhần vào "Quản lý", giao diện quản lý tù nhân trong nhà tù sẽ hiện ra như sau</h2>
<img src="https://github.com/BuiDuongKham/OOP-Final/blob/master/src/Instruction/PrisonerView_new.PNG" />
<p> Giao diện quản lý tù nhân có các chức năng như sau: thêm,sửa, xóa, đăng kí thăm nuôi, tìm kiếm (theo tên, ID), sắp xếp (theo ID, tuổi, Tên)</p>
<p> Một số quy tắc khi thêm tù nhân </p>
<ol>
    <li>  Tuổi >= 0 </li>
    <li> CMND là một dãy số 12 số không được trùng với các tù nhân khác </li>
    <li> Mức án tù tình bằng tháng và phải >0 </li>
    <li> Ngày vào tù viết theo định dạng dd/mm/yyyy và phải nhỏ hơn ngày hiện tại </li>
</ol>
    
<h3> Màn hình thăm nuôi </h3>
<img src="https://github.com/BuiDuongKham/OOP-Final/blob/master/src/Instruction/VisitorView_new.PNG" />
<p> Một số quy tắc khi thêm người thăm nuôi </p>
<ol>
    <li> Thăm nuôi không được trùng ngày với những người thăm nuôi trước </li>
    <li> Ngày xin thăm nuôi phải lớn hơn ngày hiện tại </li>
</ol>
    
<h2> Hết, em xin cảm ơn các thầy, cô ! </h2>
