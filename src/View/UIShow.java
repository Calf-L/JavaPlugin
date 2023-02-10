package View;


import action.MainAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.codehaus.plexus.util.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UIShow {



    public static void show(AnActionEvent e){
//        String[] fileNames = FileUtils.getFileNames(System.getProperty("user.dir") + "/src/fileTemplates");
        String[] fileNames={"CapacityUtils","HttpClientUtils","InitConfigFileUtils","IPUtils"
            ,"JwtUtils","MailUtils","MD5Utils","StringUtils"
        };

        JFrame jf = new JFrame("SelectUtils");

//        jf.setLayout(new FlowLayout());
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
//        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });


        JButton selectButton = new JButton("Select");
        Box BoxSelectButton = Box.createHorizontalBox();
        BoxSelectButton.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        BoxSelectButton.add(selectButton);

        JLabel jLabel = new JLabel("Select the tool class you need", JLabel.CENTER);
        Box BoxJLabel = Box.createHorizontalBox();
        BoxJLabel.add(jLabel);

        JPanel panel = new JPanel();

        // 创建一个 JList 实例
        JList<String> list = new JList<String>();


        // 设置一下首选大小
        list.setPreferredSize(new Dimension(200, 100));

        // 允许可间断的多选
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 设置选项数据（内部将自动封装成 ListModel ）
        list.setListData(fileNames);

        // 设置默认选中项
        list.setSelectedIndex(0);
        list.setVisibleRowCount(8);
        JScrollPane jScrollPane = new JScrollPane(list);


        Box BoxScrollPane = Box.createHorizontalBox();
        BoxScrollPane.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        BoxScrollPane.add(jScrollPane);



        Box box = Box.createVerticalBox();
        box.add(BoxJLabel);
        box.add(BoxScrollPane);
        box.add(BoxSelectButton);

        // 添加到内容面板容器
        panel.add(box);
        jf.add(panel);
        jf.setVisible(true);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                List<String> selectedValuesList = list.getSelectedValuesList();
                try {
                    MainAction.createDirectory("utils",selectedValuesList,e);
                }catch (Exception e){
                    jf.dispose();
                    JOptionPane.showMessageDialog(null,"Failed, try to select the edit area!","Tips",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                jf.dispose();
                JOptionPane.showMessageDialog(null,"Success!","Tips",JOptionPane.INFORMATION_MESSAGE);
            }
        });




    }
}
