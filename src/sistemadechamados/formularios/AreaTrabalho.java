package sistemadechamados.formularios;

import java.sql.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager; //
import net.sf.jasperreports.engine.JasperPrint; //
import net.sf.jasperreports.view.JasperViewer;
import sistemadechamados.dados.DbaJava; //Modulo conexão Banco de dados

/**
 * Método referente ao acesso a área de trabalho e suas funcionalidades
 *
 * @author Diego Soares da Silva
 */
public class AreaTrabalho extends javax.swing.JFrame {

    /**
     * Metodo referente ao acesso a área de trabalho e suas funcionalidades, inclusive geração de relatório em PDF.
     */
    Connection con = null;

    public AreaTrabalho() {
        initComponents();
        //Conexão com o Banco de Dados através do modulo conexão
        con = DbaJava.ligacao();
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel = new javax.swing.JDesktopPane();
        jPermissao = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jRelatorioServicos = new javax.swing.JMenuItem();
        jRelatorioClientes = new javax.swing.JMenuItem();
        jMenuca = new javax.swing.JMenu();
        jMenucli = new javax.swing.JMenuItem();
        jMenuana = new javax.swing.JMenuItem();
        jMenucha = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jChaAbert = new javax.swing.JMenuItem();
        jChaCon = new javax.swing.JMenuItem();
        jMenuajud = new javax.swing.JMenu();
        jMenuaju = new javax.swing.JMenuItem();
        jMenufe = new javax.swing.JMenu();
        jMenusai = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel.setDoubleBuffered(true);
        jPanel.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jPermissao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPermissao.setText("Permissao");

        jMenuBar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenu2.setText("Administrativo");
        jMenu2.setEnabled(false);

        jRelatorioServicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jRelatorioServicos.setText("Relatório financeiro");
        jRelatorioServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRelatorioServicosActionPerformed(evt);
            }
        });
        jMenu2.add(jRelatorioServicos);

        jRelatorioClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jRelatorioClientes.setText("Relatório de clientes");
        jRelatorioClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRelatorioClientesActionPerformed(evt);
            }
        });
        jMenu2.add(jRelatorioClientes);

        jMenuBar.add(jMenu2);

        jMenuca.setText("Cadastros");

        jMenucli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenucli.setText("Cliente");
        jMenucli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenucliActionPerformed(evt);
            }
        });
        jMenuca.add(jMenucli);

        jMenuana.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuana.setText("Analista");
        jMenuana.setEnabled(false);
        jMenuana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuanaActionPerformed(evt);
            }
        });
        jMenuca.add(jMenuana);

        jMenucha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenucha.setText("Chamado");
        jMenucha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuchaActionPerformed(evt);
            }
        });
        jMenuca.add(jMenucha);

        jMenuBar.add(jMenuca);

        jMenu1.setText("Relatório de chamados");

        jChaAbert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        jChaAbert.setText("Chamados abertos");
        jChaAbert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChaAbertActionPerformed(evt);
            }
        });
        jMenu1.add(jChaAbert);

        jChaCon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        jChaCon.setText("Chamados concluídos");
        jChaCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChaConActionPerformed(evt);
            }
        });
        jMenu1.add(jChaCon);

        jMenuBar.add(jMenu1);

        jMenuajud.setText("Ajuda");

        jMenuaju.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK));
        jMenuaju.setText("Sobre");
        jMenuaju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuajuActionPerformed(evt);
            }
        });
        jMenuajud.add(jMenuaju);

        jMenuBar.add(jMenuajud);

        jMenufe.setText("Deslogar-se");

        jMenusai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenusai.setText("Sair");
        jMenusai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenusaiActionPerformed(evt);
            }
        });
        jMenufe.add(jMenusai);

        jMenuBar.add(jMenufe);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPermissao)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPermissao))
        );

        jPanel.getAccessibleContext().setAccessibleDescription("");

        setBounds(0, 0, 970, 818);
    }// </editor-fold>//GEN-END:initComponents
//metodo sair do sistema
    private void jMenusaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenusaiActionPerformed
        int quit = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (quit == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenusaiActionPerformed

//metodo licença
    private void jMenuajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuajuActionPerformed
        // TODO add your handling code here:
        Sobre sob = new Sobre();
        sob.setVisible(true);

    }//GEN-LAST:event_jMenuajuActionPerformed

//Metodo chamar formulário clientes 
    private void jMenucliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenucliActionPerformed
        // TODO add your handling code here:
        FormClientes clientes = new FormClientes();
        clientes.setVisible(true);
        jPanel.add(clientes);
    }//GEN-LAST:event_jMenucliActionPerformed

//Metodo chamar formulário analistas
    private void jMenuanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuanaActionPerformed
        FormAnalistas analistas = new FormAnalistas();
        analistas.setVisible(true);
        jPanel.add(analistas); //Abre o formulário dentro da Area de trabalho do sistema


    }//GEN-LAST:event_jMenuanaActionPerformed

//Metodo chamar formulário chamados
    private void jMenuchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuchaActionPerformed
        FormChamados chamados = new FormChamados();
        chamados.setVisible(true);
        jPanel.add(chamados);
    }//GEN-LAST:event_jMenuchaActionPerformed

//########## Relatório de clientes com o JasperReport#######
//Relatório de clientes    
    private void jRelatorioClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRelatorioClientesActionPerformed
        int aceita = JOptionPane.showConfirmDialog(null, "Gerar relatório de clientes?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            //Impressão de relatório com o Framework JasperReports    
            try {
                //Utilização da classe JasperPrint

                JasperPrint impressao = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/RelatorioCliente.jasper"), null, con);

                //Exibe o relatório
                JasperViewer.viewReport(impressao, false);

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, f);
                //
            }

        }
    }//GEN-LAST:event_jRelatorioClientesActionPerformed

//Método Relatório de Chamados abertos
    private void jChaAbertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChaAbertActionPerformed
        int aceita = JOptionPane.showConfirmDialog(null, "Gerar relatório de chamados abertos?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            //Impressão de relatório com o Framework JasperReports    
            try {
                //Utilização da classe JasperPrint

                JasperPrint impressao = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/RelatorioChaAbertos.jasper"), null, con);

                //Exibe o relatório
                JasperViewer.viewReport(impressao, false);
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, f);

            }
        }
    }//GEN-LAST:event_jChaAbertActionPerformed

//Método Relatório de Chamados concluídos
    private void jChaConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChaConActionPerformed
        int aceita = JOptionPane.showConfirmDialog(null, "Gerar relatório de chamados concluídos?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            //Impressão de relatório com o Framework JasperReports    
            try {
                //Utilização da classe JasperPrint

                JasperPrint impressao = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/RelatorioChaConclu.jasper"), null, con);

                //Exibe o relatório
                JasperViewer.viewReport(impressao, false);

            } catch (Exception v) {
                JOptionPane.showMessageDialog(null, v);

            }
    }//GEN-LAST:event_jChaConActionPerformed
    }
    //Relatório financeiro
    private void jRelatorioServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRelatorioServicosActionPerformed
        int aceita = JOptionPane.showConfirmDialog(null, "Gerar o relatório financeiro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            //Impressão de relatório com o Framework JasperReports    
            try {
                //Utilização da classe JasperPrint

               JasperPrint impressao = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/RelatorioFin.jasper"), null, con);

                //Exibe o relatório
                JasperViewer.viewReport(impressao, false);

            } catch (Exception v) {
                JOptionPane.showMessageDialog(null, v);
            }
        }
    }//GEN-LAST:event_jRelatorioServicosActionPerformed

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
            java.util.logging.Logger.getLogger(AreaTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AreaTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AreaTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreaTrabalho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jChaAbert;
    private javax.swing.JMenuItem jChaCon;
    private javax.swing.JMenu jMenu1;
    public static javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuaju;
    private javax.swing.JMenu jMenuajud;
    public static javax.swing.JMenuItem jMenuana;
    private javax.swing.JMenu jMenuca;
    private javax.swing.JMenuItem jMenucha;
    private javax.swing.JMenuItem jMenucli;
    private javax.swing.JMenu jMenufe;
    private javax.swing.JMenuItem jMenusai;
    private javax.swing.JDesktopPane jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel jPermissao;
    private javax.swing.JMenuItem jRelatorioClientes;
    private javax.swing.JMenuItem jRelatorioServicos;
    // End of variables declaration//GEN-END:variables
}
