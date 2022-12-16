package sistemadechamados.formularios;

import java.sql.*; //biblioteca SQL
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; //limpeza tabela superior
import net.proteanit.sql.DbUtils; //Biblioteca para pesquisa avançada de itens
import sistemadechamados.dados.DbaJava; //comunicação com o banco

/**
 * Método referente a tela de clientes
 *
 * @author Diego Soares da Silva
 */
public class FormClientes extends javax.swing.JInternalFrame {

    /**
     * Metodo formulário dos clientes.
     */

    Connection con = null; //Modulo de conexão
    PreparedStatement pp = null; ////Preprara o banco de dados para receber os dados da tela de login
    ResultSet result = null; //Resultado da consulta ao banco

    public FormClientes() {
        initComponents();
        con = DbaJava.ligacao(); //chamando o metodo conector
    }

//Metodo acrescentar clientes
    private void acrescentarCli() {
        String bd = "INSERT INTO tclientes (nomecliente,email,endereco,bairro,cidade,estado,telefone)values(?,?,?,?,?,?,?)";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jNomeCli.getText());
            pp.setString(2, jEmailCli.getText());
            pp.setString(3, jEndCli.getText());
            pp.setString(4, jBairroCli.getText());
            pp.setString(5, jCidadeCli.getText());
            pp.setString(6, jEstadoCli.getSelectedItem().toString());
            pp.setString(7, jTelefoneCli.getText());

            if ((jNomeCli.getText().isEmpty()) || (jTelefoneCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "*Campo obrigatório não preenchido");
            } else {

                int acrescenta = pp.executeUpdate();
                if (acrescenta > 0) {//Se campo valor acrescentado é maior que 0, então, cadastra
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado");
                    //Limpa campos
                    jIDClientes.setText(null);
                    jNomeCli.setText(null);
                    jEmailCli.setText(null);
                    jEndCli.setText(null);
                    jBairroCli.setText(null);
                    jCidadeCli.setText(null);
                    jTelefoneCli.setText(null);
                    ((DefaultTableModel) jTabelCli.getModel()).setRowCount(0); //limpar formulário superior
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//Metodo pesquisar clientes
    private void pesqCli() {
        String bd = "SELECT *FROM tclientes WHERE nomecliente like ?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jPesqCli.getText() + "%"); //$ continuação comando sql
            result = pp.executeQuery();
            jTabelCli.setModel(DbUtils.resultSetToTableModel(result)); //Uso da biblioteca de pesquisa avançada de itens

        } catch (Exception b) {
            JOptionPane.showMessageDialog(null, b);
        }
    }
//Metodo passa o conteúdo do formulário para a tabela

    private void atualizacaoCli() {
        int atualiza = jTabelCli.getSelectedRow();
        jIDClientes.setText(jTabelCli.getModel().getValueAt(atualiza, 0).toString());
        jNomeCli.setText(jTabelCli.getModel().getValueAt(atualiza, 1).toString());
        jEmailCli.setText(jTabelCli.getModel().getValueAt(atualiza, 2).toString());
        jEndCli.setText(jTabelCli.getModel().getValueAt(atualiza, 3).toString());
        jBairroCli.setText(jTabelCli.getModel().getValueAt(atualiza, 4).toString());
        jCidadeCli.setText(jTabelCli.getModel().getValueAt(atualiza, 5).toString());
        jEstadoCli.setSelectedItem(jTabelCli.getModel().getValueAt(atualiza, 6).toString());
        jTelefoneCli.setText(jTabelCli.getModel().getValueAt(atualiza, 7).toString());

        jAcresCli.setEnabled(false);//bloqueia botão adicionar para evitar duplicidade
        //Após pesquisar, habilta os campos remover e editar
        jRemovCli.setEnabled(true);
        jEditarCli.setEnabled(true);
    }
//Metodo editar informações dos clientes

    private void editarCli() {
        String bd = "UPDATE tclientes SET nomecliente=?,email=?,endereco=?,bairro=?,cidade=?,estado=?,telefone=? where idclientes=?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jNomeCli.getText());
            pp.setString(2, jEmailCli.getText());
            pp.setString(3, jEndCli.getText());
            pp.setString(4, jBairroCli.getText());
            pp.setString(5, jCidadeCli.getText());
            pp.setString(6, jEstadoCli.getSelectedItem().toString());

            pp.setString(7, jTelefoneCli.getText());
            pp.setString(8, jIDClientes.getText());

            if ((jNomeCli.getText().isEmpty()) || (jTelefoneCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, " *Campo obrigatório não preenchido");
            } else {

                int acrescenta = pp.executeUpdate(); //campo valor atualiazado é maior que 0, então, cadastra
                if (acrescenta > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso");
                    //Limpa campos
                    jIDClientes.setText(null);
                    jNomeCli.setText(null);
                    jEmailCli.setText(null);
                    jEndCli.setText(null);
                    jBairroCli.setText(null);
                    jCidadeCli.setText(null);
                    jTelefoneCli.setText(null);
                    jAcresCli.setEnabled(true);
                    ((DefaultTableModel) jTabelCli.getModel()).setRowCount(0); //limpar formulário superior

                    //Após editar, desabita os botões remover e editar, voltando para a tela adicionar ou pesquisar
                    jRemovCli.setEnabled(false);
                    jEditarCli.setEnabled(false);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//Metodo excluir clientes
    private void excluirCli() {
        int confir_exclus = JOptionPane.showConfirmDialog(null, "Deseja mesmo remover o cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confir_exclus == JOptionPane.YES_NO_OPTION) {
            String bd = "DELETE FROM tclientes WHERE idclientes=? ";
            try {
                pp = con.prepareStatement(bd);
                pp.setString(1, jIDClientes.getText());
                int removido = pp.executeUpdate();
                if (removido > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
                    //Limpa os campos da tela
                    jIDClientes.setText(null);
                    jNomeCli.setText(null);
                    jEmailCli.setText(null);
                    jEndCli.setText(null);
                    jBairroCli.setText(null);
                    jCidadeCli.setText(null);
                    jTelefoneCli.setText(null);
                    jAcresCli.setEnabled(true);

                    ((DefaultTableModel) jTabelCli.getModel()).setRowCount(0); //limpar formulário superior
                    //######testes
                    jRemovCli.setEnabled(false);
                    jEditarCli.setEnabled(false);

                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jEmailCli = new javax.swing.JTextField();
        jEndCli = new javax.swing.JTextField();
        jTelefoneCli = new javax.swing.JTextField();
        jNomeCli = new javax.swing.JTextField();
        jCidadeCli = new javax.swing.JTextField();
        jBairroCli = new javax.swing.JTextField();
        jAcresCli = new javax.swing.JButton();
        jRemovCli = new javax.swing.JButton();
        jEditarCli = new javax.swing.JButton();
        jPesqCli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelCli = new javax.swing.JTable();
        jEstadoCli = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jIDClientes = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Sistema de chamados");
        setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        setPreferredSize(new java.awt.Dimension(803, 697));

        jLabel1.setText("Nome*");

        jLabel2.setText("E-mail");

        jLabel3.setText("Endereço");

        jLabel4.setText("Bairro");

        jLabel5.setText("Cidade");

        jLabel6.setText("Estado");

        jLabel7.setText("Telefone*");

        jAcresCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/adicionar.png"))); // NOI18N
        jAcresCli.setToolTipText("Adicionar cliente");
        jAcresCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcresCliActionPerformed(evt);
            }
        });

        jRemovCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/remover.png"))); // NOI18N
        jRemovCli.setToolTipText("Remover cliente");
        jRemovCli.setEnabled(false);
        jRemovCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemovCliActionPerformed(evt);
            }
        });

        jEditarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/editar.png"))); // NOI18N
        jEditarCli.setToolTipText("Editar cliente");
        jEditarCli.setEnabled(false);
        jEditarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditarCliActionPerformed(evt);
            }
        });

        jPesqCli.setToolTipText("Pesquise um cliente aqui ou cadastre um novo na tela abaixo");
        jPesqCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPesqCliKeyReleased(evt);
            }
        });

        jLabel8.setText("Pesquisar cliente:");

        jTabelCli = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColIndex){
                return false;
            }
        };
        jTabelCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "E-mail", "Endereço", "Bairro", "Cidade", "Estado", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelCli.setFocusable(false);
        jTabelCli.getTableHeader().setReorderingAllowed(false);
        jTabelCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelCliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelCli);

        jEstadoCli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEstadoCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jEstadoCli.setSelectedIndex(20);

        jLabel9.setText("ID");

        jIDClientes.setEditable(false);
        jIDClientes.setToolTipText("Campo Automático");
        jIDClientes.setEnabled(false);

        jLabel10.setText("*Campo obrigatório");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 104, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jIDClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jPesqCli))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jAcresCli)
                                .addGap(32, 32, 32)
                                .addComponent(jRemovCli)
                                .addGap(36, 36, 36)
                                .addComponent(jEditarCli))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCidadeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBairroCli, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jEstadoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)))
                .addGap(50, 50, 50))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jAcresCli, jEditarCli, jRemovCli});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jPesqCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jIDClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jBairroCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCidadeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEstadoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAcresCli)
                    .addComponent(jRemovCli)
                    .addComponent(jEditarCli))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jAcresCli, jEditarCli, jRemovCli});

        setBounds(0, 0, 799, 744);
    }// </editor-fold>//GEN-END:initComponents

//Metodo chamar adição de clientes   
    private void jAcresCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcresCliActionPerformed
        acrescentarCli();
    }//GEN-LAST:event_jAcresCliActionPerformed

//Metodo chamar pesquisar clientes em tempo real
    private void jPesqCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPesqCliKeyReleased
        pesqCli();
    }//GEN-LAST:event_jPesqCliKeyReleased

//Metodo chamar atualização da tabela com os valores na tela
    private void jTabelCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelCliMouseClicked
        atualizacaoCli();
    }//GEN-LAST:event_jTabelCliMouseClicked

//Metodo chamar edição de clientes
    private void jEditarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditarCliActionPerformed
        editarCli();
    }//GEN-LAST:event_jEditarCliActionPerformed
//Metodo chamar exclusão de clientes
    private void jRemovCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemovCliActionPerformed
        excluirCli();
    }//GEN-LAST:event_jRemovCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAcresCli;
    private javax.swing.JTextField jBairroCli;
    private javax.swing.JTextField jCidadeCli;
    private javax.swing.JButton jEditarCli;
    private javax.swing.JTextField jEmailCli;
    private javax.swing.JTextField jEndCli;
    private javax.swing.JComboBox<String> jEstadoCli;
    private javax.swing.JTextField jIDClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNomeCli;
    private javax.swing.JTextField jPesqCli;
    private javax.swing.JButton jRemovCli;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelCli;
    private javax.swing.JTextField jTelefoneCli;
    // End of variables declaration//GEN-END:variables
}
