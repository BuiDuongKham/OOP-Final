# OOP-Final
Bài làm của Bùi Dương Khâm lớp tín chỉ OOP JAVA N08, mã sinh viên 21011219, lớp niên chế K15-KHMT
Lưu ý: Bài làm hoàn toàn trên IDE Intellij IDEA nên có thể sẽ chưa tương thích với các IDE khác. Nếu có một số lỗi cài đặt xảy ra, xin cô (thầy) cố gắng khắc phục. Em xin cảm ơn!
Cấu trúc project (chỉ tính phần src)
File chạy chương trình chính: App.java
3 File lần lượt lưu trữ thông tin của nhà tù, tù nhân, người thăm nuôi tù nhân: prison.xml, prisoner.xml, visitor.xml
    -Package View: lưu trữ các giao diện hiển thị (4 giao diện: giao diện quản lý các nhà tù, giao diện quản lý các tù nhân, giao diện đăng kí thăm nuôi tù nhân, giao diện tổng hợp các lượt đăng kí thăm nuôi).
    -Package Entity: lưu trữ các đối tượng (3 đối tượng: nhà tù, tù nhân và người thăm nuôi) và các class xml để đọc đối tượng đó. Ví dụ như đối tượng tù nhân có file Prison.java để tạo đối tượng và file PrisonXML.java để đọc đối tượng.
    -Package Controller: là nơi xử lý logic chính của chương trình. Người dùng tương tác với view được điều khiển bởi Controller.
    -Package Utils: lưu trữ các hàm tiện ích để đọc, ghi file XML
    -Package Func: lưu trữ các đối tượng để sử dụng file Utils để đọc, ghi dữ liệu (khi sử dụng thường được đặt hậu tố Dao (Data Accessing Object) đằng sau 
    -Package Store: lưu trữ các đối tượng lưu trữ chính. Những file này sẽ chịu trách nhiệm chính, sử dụng các file Func để tương tác với Controller.
    
    
## Work Flow
