/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.edusys.ui;


import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class MainWindows extends javax.swing.JFrame {

    /**
     * Creates new form MainWindows
     */
    public MainWindows() {
        initComponents();
        startClock();
        init();
    }
    void startClock() {
        class TimeClock extends Thread {

            @Override
            public void run() {
                while (true) {
                    lblClock1.setText(new SimpleDateFormat("hh:mm:ss a").format(Calendar.getInstance().getTime()));
                }
            }
        }
        TimeClock timeClock = new TimeClock();
        timeClock.start();
    }
    
    void init(){
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");
    }
    private void closeAllInternalFrames() {
        JInternalFrame[] frames = Desktop.getAllFrames();
        for (JInternalFrame frame : frames) {
            frame.dispose();
        }
    }
    
    void openDoiMatKhau(){
        if (Auth.isLogin()) {
            DoiMatKhau doiMK = new DoiMatKhau();
        openWindow(Desktop, doiMK);
            doiMK.setVisible(true);
        }else{
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }
    
    void openLogin(){
        Login DN = new Login();
        openWindow(Desktop, DN);
        DN.setVisible(true);
    }
   
    void logout(){
        closeAllInternalFrames();
        Auth.clear();
        openLogin();
    }
//    
    void KetThuc(){
        if (MsgBox.confirm(this, "Bạn muốn kết thúc việc làm ?")) {
            System.exit(0);
        }
    }
    
    public void openchuyende() {
        if(Auth.isLogin()){
            QuanLy_ChuyenDe qlcd = new QuanLy_ChuyenDe();
        openWindow(Desktop, qlcd);
            qlcd.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhập");
        }             
    }
    
    public void openkhoahoc() {
        if(Auth.isLogin()){
            QuanLy_KhoaHoc qlkh = new QuanLy_KhoaHoc();
        openWindow(Desktop, qlkh);
            qlkh.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhập");
        }                          
    }
    
    public void opennguoihoc() {
        if(Auth.isLogin()){
            QuanLy_NguoiHoc qlnh = new QuanLy_NguoiHoc();
        openWindow(Desktop, qlnh);
            qlnh.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhập");
        }                           
    }
    
    public void openhocvien() {
        if(Auth.isLogin()){
            QuanLy_HocVien qlhv = new QuanLy_HocVien();
        openWindow(Desktop, qlhv);
            qlhv.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhập");
        }                     
    }
    
    public void opennhanvien() {
        if(Auth.isLogin()){
            QuanLy_NhanVien qlnv = new QuanLy_NhanVien();
        openWindow(Desktop, qlnv);
            qlnv.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhậ­p");
        }          
    }
    public void openThongKe(int index) {
    if (Auth.isLogin()) {
        if (index == 3 && !Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền xem doanh thu!");
            return; // Thêm câu lệnh này để kết thúc phương thức sau khi hiển thị thông báo
        } else {
            ThongKe tk = new ThongKe();
            openWindow(Desktop, tk);
            tk.selectTab(index);
        }
    } else {
        MsgBox.alert(this, "Vui lòng đăng nhập lại!");
    }
}
    
    
       public void opengioithieu(){
        if(Auth.isLogin()){
            GioiThieu gioithieu = new GioiThieu();
        openWindow(Desktop, gioithieu);
            gioithieu.setVisible(true);
        }else{
            MsgBox.alert(this,"Vui lòng đăng nhập");
        }      
    }
       
       public void openHuongdan() {
    if (!java.awt.Desktop.isDesktopSupported()) {
        JOptionPane.showMessageDialog(this, "Desktop API không được hỗ trợ trên nền tảng này.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    if (!desktop.isSupported(java.awt.Desktop.Action.OPEN)) {
        JOptionPane.showMessageDialog(this, "Hành động mở file không được hỗ trợ trên nền tảng này.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {

        File file = new File("help/index.html");
        if (!file.exists()) {
            throw new FileNotFoundException("File hướng dẫn không tồn tại.");
        }
        desktop.open(file);
    } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(this, "File hướng dẫn không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi mở file hướng dẫn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Một lỗi không xác định đã xảy ra.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
}
       
       private void openWindow(JDesktopPane desktopPane, JInternalFrame jInternalFrame){
        Dimension desktopSize = desktopPane.getSize();
        System.out.println("Desktop size width= "+desktopSize.width);
        System.out.println("Desktop size height= "+desktopSize.height);
        
        Dimension jInternalFrameSize = jInternalFrame.getSize();
        System.out.println("Component size width= "+jInternalFrameSize.width);
        System.out.println("Component size height= "+jInternalFrameSize.height);
        
        int x =(desktopSize.width - jInternalFrameSize.width)/2;
        int y =(desktopSize.height - jInternalFrameSize.height)/2;
        
        System.out.println("Location x="+x);
        System.out.println("Location y="+y);
        
        jInternalFrame.setLocation(x,y);
        
        desktopPane.add(jInternalFrame).setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        btnDangxuat = new javax.swing.JButton();
        btnStop1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnChuyenDe1 = new javax.swing.JButton();
        btnNguoiHoc1 = new javax.swing.JButton();
        btnKhoaHoc = new javax.swing.JButton();
        btnHocVien = new javax.swing.JButton();
        btnChuyenDe = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnHuongDan1 = new javax.swing.JButton();
        Desktop = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblClock1 = new javax.swing.JLabel();
        mnbmenu = new javax.swing.JMenuBar();
        mnuHethong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mniDKM = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniThoat = new javax.swing.JMenuItem();
        mnuQuanly = new javax.swing.JMenu();
        mniChuyenDe = new javax.swing.JMenuItem();
        mniKhoaHoc = new javax.swing.JMenuItem();
        mniNguoiHoc = new javax.swing.JMenuItem();
        mniHocVien = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniNhanVien = new javax.swing.JMenuItem();
        mnuThongke = new javax.swing.JMenu();
        mniBangDiem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniLuongNguoiHoc = new javax.swing.JMenuItem();
        mniDiemChuyenDe = new javax.swing.JMenuItem();
        mniDoanhThu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mniGTSP = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);

        btnDangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Exit.png"))); // NOI18N
        btnDangxuat.setText("Đăng xuất");
        btnDangxuat.setToolTipText("");
        btnDangxuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangxuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangxuat);

        btnStop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Stop.png"))); // NOI18N
        btnStop1.setText("Kết thúc");
        btnStop1.setFocusable(false);
        btnStop1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStop1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStop1);
        jToolBar1.add(jSeparator2);

        btnChuyenDe1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Lists.png"))); // NOI18N
        btnChuyenDe1.setText("Chuyên đề");
        btnChuyenDe1.setFocusable(false);
        btnChuyenDe1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChuyenDe1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChuyenDe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenDe1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChuyenDe1);

        btnNguoiHoc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Conference.png"))); // NOI18N
        btnNguoiHoc1.setText("Người học");
        btnNguoiHoc1.setFocusable(false);
        btnNguoiHoc1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNguoiHoc1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNguoiHoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNguoiHoc1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNguoiHoc1);

        btnKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Certificate.png"))); // NOI18N
        btnKhoaHoc.setText("Khóa học");
        btnKhoaHoc.setFocusable(false);
        btnKhoaHoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoaHoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaHocActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKhoaHoc);

        btnHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/User.png"))); // NOI18N
        btnHocVien.setText("Học viên");
        btnHocVien.setFocusable(false);
        btnHocVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHocVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHocVienActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHocVien);

        btnChuyenDe.setFocusable(false);
        btnChuyenDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChuyenDe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenDeActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChuyenDe);
        jToolBar1.add(jSeparator3);

        btnHuongDan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Globe.png"))); // NOI18N
        btnHuongDan1.setText("Hướng dẫn");
        btnHuongDan1.setFocusable(false);
        btnHuongDan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuongDan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongDan1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHuongDan1);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Info.png"))); // NOI18N
        jLabel1.setText("Hệ quản lý đào tạo");

        lblClock1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Alarm.png"))); // NOI18N
        lblClock1.setText("00:00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblClock1)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblClock1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Desktop.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopLayout.createSequentialGroup()
                .addGap(0, 479, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnuHethong.setText("Hệ thống");

        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHethong.add(mniDangNhap);

        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHethong.add(mniDangXuat);
        mnuHethong.add(jSeparator7);

        mniDKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Refresh.png"))); // NOI18N
        mniDKM.setText("Đổi mật khẩu");
        mniDKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDKMActionPerformed(evt);
            }
        });
        mnuHethong.add(mniDKM);
        mnuHethong.add(jSeparator1);

        mniThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Stop.png"))); // NOI18N
        mniThoat.setText("Thoát");
        mniThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThoatActionPerformed(evt);
            }
        });
        mnuHethong.add(mniThoat);

        mnbmenu.add(mnuHethong);

        mnuQuanly.setText("Quản lý");
        mnuQuanly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuQuanlyActionPerformed(evt);
            }
        });

        mniChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Lists.png"))); // NOI18N
        mniChuyenDe.setText("Chuyên đề");
        mniChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniChuyenDeActionPerformed(evt);
            }
        });
        mnuQuanly.add(mniChuyenDe);

        mniKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Certificate.png"))); // NOI18N
        mniKhoaHoc.setText("Khóa học");
        mniKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhoaHocActionPerformed(evt);
            }
        });
        mnuQuanly.add(mniKhoaHoc);

        mniNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Conference.png"))); // NOI18N
        mniNguoiHoc.setText("Người học");
        mniNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNguoiHocActionPerformed(evt);
            }
        });
        mnuQuanly.add(mniNguoiHoc);

        mniHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/User.png"))); // NOI18N
        mniHocVien.setText("Học viên");
        mniHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHocVienActionPerformed(evt);
            }
        });
        mnuQuanly.add(mniHocVien);
        mnuQuanly.add(jSeparator4);

        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/User group.png"))); // NOI18N
        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        mnuQuanly.add(mniNhanVien);

        mnbmenu.add(mnuQuanly);

        mnuThongke.setText("Thống kê");
        mnuThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuThongkeActionPerformed(evt);
            }
        });

        mniBangDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Card file.png"))); // NOI18N
        mniBangDiem.setText("Bảng điểm");
        mniBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBangDiemActionPerformed(evt);
            }
        });
        mnuThongke.add(mniBangDiem);
        mnuThongke.add(jSeparator5);

        mniLuongNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Clien list.png"))); // NOI18N
        mniLuongNguoiHoc.setText("Lượng người học");
        mniLuongNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLuongNguoiHocActionPerformed(evt);
            }
        });
        mnuThongke.add(mniLuongNguoiHoc);

        mniDiemChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Bar chart.png"))); // NOI18N
        mniDiemChuyenDe.setText("Điểm chuyên đề");
        mniDiemChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDiemChuyenDeActionPerformed(evt);
            }
        });
        mnuThongke.add(mniDiemChuyenDe);

        mniDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Dollar.png"))); // NOI18N
        mniDoanhThu.setText("Doanh thu");
        mniDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoanhThuActionPerformed(evt);
            }
        });
        mnuThongke.add(mniDoanhThu);

        mnbmenu.add(mnuThongke);

        jMenu2.setText("Trợ giúp");

        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        jMenu2.add(mniHuongDan);
        jMenu2.add(jSeparator6);

        mniGTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icons/Brick house.png"))); // NOI18N
        mniGTSP.setText("Giới thiệu sản phẩm");
        mniGTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGTSPActionPerformed(evt);
            }
        });
        jMenu2.add(mniGTSP);

        mnbmenu.add(jMenu2);

        setJMenuBar(mnbmenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(Desktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Desktop))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
        closeAllInternalFrames();
        logout();
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniChuyenDeActionPerformed
        // TODO add your handling code here:
        openchuyende();
    }//GEN-LAST:event_mniChuyenDeActionPerformed

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed
        // TODO add your handling code here:
        opennhanvien();
    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void mniBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBangDiemActionPerformed
        // TODO add your handling code here:
        openThongKe(0);
    }//GEN-LAST:event_mniBangDiemActionPerformed

    private void mniThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThoatActionPerformed
        // TODO add your handling code here:
        KetThuc();
    }//GEN-LAST:event_mniThoatActionPerformed

    private void mniKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhoaHocActionPerformed
        // TODO add your handling code here:
        openkhoahoc();
    }//GEN-LAST:event_mniKhoaHocActionPerformed

    private void btnChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenDeActionPerformed
        // TODO add your handling code here:
        openchuyende();
    }//GEN-LAST:event_btnChuyenDeActionPerformed

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:
//        openLogin();
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mnuQuanlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuQuanlyActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuQuanlyActionPerformed

    private void mnuThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuThongkeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuThongkeActionPerformed

    private void mniDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoanhThuActionPerformed
        // TODO add your handling code here:
        openThongKe(3);
    }//GEN-LAST:event_mniDoanhThuActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        // TODO add your handling code here:
        openHuongdan();
    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void mniDiemChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDiemChuyenDeActionPerformed
        // TODO add your handling code here:
        openThongKe(2);
    }//GEN-LAST:event_mniDiemChuyenDeActionPerformed

    private void mniLuongNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLuongNguoiHocActionPerformed
        // TODO add your handling code here:
        openThongKe(1);
    }//GEN-LAST:event_mniLuongNguoiHocActionPerformed

    private void mniNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNguoiHocActionPerformed
        // TODO add your handling code here:
        opennguoihoc();
    }//GEN-LAST:event_mniNguoiHocActionPerformed

    private void mniHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHocVienActionPerformed
        // TODO add your handling code here:
        openhocvien();
    }//GEN-LAST:event_mniHocVienActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        openLogin();
    }//GEN-LAST:event_formWindowOpened

    private void mniGTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGTSPActionPerformed
        // TODO add your handling code here:
        opengioithieu();
    }//GEN-LAST:event_mniGTSPActionPerformed

    private void mniDKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDKMActionPerformed
        // TODO add your handling code here:
        openDoiMatKhau();
    }//GEN-LAST:event_mniDKMActionPerformed

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void btnStop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStop1ActionPerformed
        // TODO add your handling code here:
        KetThuc();
    }//GEN-LAST:event_btnStop1ActionPerformed

    private void btnChuyenDe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenDe1ActionPerformed
        // TODO add your handling code here:
        openchuyende();
    }//GEN-LAST:event_btnChuyenDe1ActionPerformed

    private void btnNguoiHoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNguoiHoc1ActionPerformed
        // TODO add your handling code here:
        opennguoihoc();
    }//GEN-LAST:event_btnNguoiHoc1ActionPerformed

    private void btnKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaHocActionPerformed
        // TODO add your handling code here:
        openkhoahoc();
    }//GEN-LAST:event_btnKhoaHocActionPerformed

    private void btnHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHocVienActionPerformed
        // TODO add your handling code here:
        openhocvien();
    }//GEN-LAST:event_btnHocVienActionPerformed

    private void btnHuongDan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongDan1ActionPerformed
        // TODO add your handling code here:
        openHuongdan();
    }//GEN-LAST:event_btnHuongDan1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton btnChuyenDe;
    private javax.swing.JButton btnChuyenDe1;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnHocVien;
    private javax.swing.JButton btnHuongDan1;
    private javax.swing.JButton btnKhoaHoc;
    private javax.swing.JButton btnNguoiHoc1;
    private javax.swing.JButton btnStop1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblClock1;
    private javax.swing.JMenuBar mnbmenu;
    private javax.swing.JMenuItem mniBangDiem;
    private javax.swing.JMenuItem mniChuyenDe;
    private javax.swing.JMenuItem mniDKM;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDiemChuyenDe;
    private javax.swing.JMenuItem mniDoanhThu;
    private javax.swing.JMenuItem mniGTSP;
    private javax.swing.JMenuItem mniHocVien;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKhoaHoc;
    private javax.swing.JMenuItem mniLuongNguoiHoc;
    private javax.swing.JMenuItem mniNguoiHoc;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenuItem mniThoat;
    private javax.swing.JMenu mnuHethong;
    private javax.swing.JMenu mnuQuanly;
    private javax.swing.JMenu mnuThongke;
    // End of variables declaration//GEN-END:variables
}
