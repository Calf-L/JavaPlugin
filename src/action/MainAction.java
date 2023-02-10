package action;

import View.UIShow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;

import java.util.List;

public class MainAction extends AnAction {



    @Override
    public void actionPerformed(AnActionEvent e) {
        UIShow.show(e);
    }



    public static void createDirectory(String directoryname, List<String> name, AnActionEvent e) throws Exception {
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        PsiFile psiFile = PsiDocumentManager.getInstance(e.getProject()).getPsiFile(editor.getDocument());

        PsiJavaFile javaFile = (PsiJavaFile) psiFile;
        PsiDirectory containerDirectory = javaFile.getContainingDirectory();


        WriteCommandAction.runWriteCommandAction(e.getProject(), new Runnable() {
            @Override
            public void run() {

                    PsiDirectory subdirectory = containerDirectory.createSubdirectory(directoryname);
                    for (int i = 0; i < name.size(); i++) {
                        try {
                            createClass(subdirectory, name.get(i),e);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }




            }
        });


    }


    public  static void createClass(PsiDirectory subdirectory, String name,AnActionEvent e)throws Exception {
        WriteCommandAction.runWriteCommandAction(e.getProject(), new Runnable() {
            @Override
            public void run() {

                    JavaDirectoryService.getInstance().createClass(subdirectory, "", name);



            }
        });
    }


}
