package sistemadechamados.formularios;

import java.sql.*; //Biblioteca SQL
import javax.swing.JOptionPane; // tratamento exceção
import sistemadechamados.dados.DbaJava; //comunicação com o banco

/**
 * Método referente a tela de analistas
 *
 * @author Diego Soares da Silva
 */
public class FormAnalistas extends javax.swing.JInternalFrame {

    /**
     * Metodo formulário dos analistas.
     */

    Connection con = null; //Modulo de conexão
    PreparedStatement pp = null; ////Preprara o banco de dados para receber os dados da tela de login
    ResultSet result = null; //Resultado da consulta ao banco

    public FormAnalistas() {
        initComponents();
        con = DbaJava.ligacao(); //chamando o metodo conector

    }

//Metodo consultar analistas
    private void procurarAna() {
        String bd = "SELECT *FROM tanalistas where idanalista =?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jIDAna.getText());
            result = pp.executeQuery();
            if (result.next()) {
                jAcresAna.setEnabled(false);
                jNomeAna.setText(result.getString(2));
                jEmailAna.setText(result.getString(3));
                jEndAna.setText(result.getString(4));
                jBairroAna.setText(result.getString(5));
                jCidadeAna.setText(result.getString(6));
                jEstadoAna.setSelectedItem(result.getString(7));
                jTelefoneAna.setText(result.getString(8));
                jLoginAna.setText(result.getString(9));
                jSenhaAna.setText(result.getString(10));
                jPermiAna.setSelectedItem(result.getString(11));

                //##Testes
                jRemovAna.setEnabled(true);
                jEditarAna.setEnabled(true);

            } else {
                //tratamento de exceção e limpeza dos campos
                JOptionPane.showMessageDialog(null, "Usuário não existe");
                jAcresAna.setEnabled(true);
                jNomeAna.setText(null);
                jEmailAna.setText(null);
                jEndAna.setText(null);
                jBairroAna.setText(null);
                jCidadeAna.setText(null);
                jTelefoneAna.setText(null);
                jLoginAna.setText(null);
                jSenhaAna.setText(null);

                ///testes
                jAcresAna.setEnabled(true);
                jRemovAna.setEnabled(false);
                jEditarAna.setEnabled(false);

            }
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, f);
        }
    }
//Metodo acrescentar analistas

    private void acrescentar() {
        String bd = "INSERT INTO tanalistas (idanalista,analista,email,endereco,bairro,cidade,estado,telefone,login,senha,permissao)values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jIDAna.getText()); //setar com o conteudo do campo
            pp.setString(2, jNomeAna.getText());
            pp.setString(3, jEmailAna.getText());
            pp.setString(4, jEndAna.getText());
            pp.setString(5, jBairroAna.getText());
            pp.setString(6, jCidadeAna.getText());
            pp.setString(7, jEstadoAna.getSelectedItem().toString());
            pp.setString(8, jTelefoneAna.getText());
            pp.setString(9, jLoginAna.getText());
            pp.setString(10, jSenhaAna.getText());
            pp.setString(11, jPermiAna.getSelectedItem().toString());

            if (((((jIDAna.getText().isEmpty()) || (jNomeAna.getText().isEmpty())) || (jTelefoneAna.getText().isEmpty())) || (jLoginAna.getText().isEmpty())) || (jSenhaAna.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, " *Campo obrigatório não preenchido");
            } else {

                int acrescenta = pp.executeUpdate(); //campo valor acrescentado é maior que 0, então, cadastra
                if (acrescenta > 0) {
                    JOptionPane.showMessageDialog(null, "Novo analista cadastrado");
                    jRemovAna.setEnabled(false);
                    jEditarAna.setEnabled(false);
                    //Limpa campos
                    jIDAna.setText(null);
                    jNomeAna.setText(null);
                    jEmailAna.setText(null);
                    jEndAna.setText(null);
                    jBairroAna.setText(null);
                    jCidadeAna.setText(null);
                    jTelefoneAna.setText(null);
                    jLoginAna.setText(null);
                    jSenhaAna.setText(null);
                }
            }

        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }
//Metodo editar analistas    

    private void editar() {
        String bd = "UPDATE tanalistas SET analista=?,email=?,endereco=?,bairro=?,cidade=?,estado=?,telefone=?,login=?,senha=?,permissao=? WHERE idanalista=?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jNomeAna.getText());
            pp.setString(2, jEmailAna.getText());
            pp.setString(3, jEndAna.getText());
            pp.setString(4, jBairroAna.getText());
            pp.setString(5, jCidadeAna.getText());
            pp.setString(6, jEstadoAna.getSelectedItem().toString());;
            pp.setString(7, jTelefoneAna.getText());
            pp.setString(8, jLoginAna.getText());
            pp.setString(9, jSenhaAna.getText());
            pp.setString(10, jPermiAna.getSelectedItem().toString());
            pp.setString(11, jIDAna.getText());

            if (((((jIDAna.getText().isEmpty()) || (jNomeAna.getText().isEmpty())) || (jTelefoneAna.getText().isEmpty())) || (jLoginAna.getText().isEmpty())) || (jSenhaAna.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, " *Campo obrigatório não preenchido");
            } else {

                int acrescenta = pp.executeUpdate(); //campo valor atualiazado é maior que 0, então, cadastra
                if (acrescenta > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso");
                    //Limpa campos
                    jAcresAna.setEnabled(true);
                    jIDAna.setText(null);
                    jNomeAna.setText(null);
                    jEmailAna.setText(null);
                    jEndAna.setText(null);
                    jBairroAna.setText(null);
                    jCidadeAna.setText(null);
                    jTelefoneAna.setText(null);
                    jLoginAna.setText(null);
                    jSenhaAna.setText(null);

                    //###Testes
                    jRemovAna.setEnabled(false);
                    jEditarAna.setEnabled(false);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//Metodo excluir analistas

    private void excluir() {
        int confir_exclus = JOptionPane.showConfirmDialog(null, "Deseja mesmo remover o usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confir_exclus == JOptionPane.YES_NO_OPTION) {
            String bd = "DELETE FROM tanalistas WHERE idanalista=? ";
            try {
                pp = con.prepareStatement(bd);
                pp.setString(1, jIDAna.getText());
                int removido = pp.executeUpdate();
                if (removido > 0) {
                    JOptionPane.showMessageDialog(null, "Analista excluído com sucesso");
                    //Limpa os campos da tela
                    jAcresAna.setEnabled(true);
                    jIDAna.setText(null);
                    jNomeAna.setText(null);
                    jEmailAna.setText(null);
                    jEndAna.setText(null);
                    jBairroAna.setText(null);
                    jCidadeAna.setText(null);
                    jTelefoneAna.setText(null);
                    jLoginAna.setText(null);
                    jSenhaAna.setText(null);

                    //######testes
                    jRemovAna.setEnabled(false);
                    jEditarAna.setEnabled(false);
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCidadeAna = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLoginAna = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jIDAna = new javax.swing.JTextField();
        jNomeAna = new javax.swing.JTextField();
        jEmailAna = new javax.swing.JTextField();
        jEndAna = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTelefoneAna = new javax.swing.JTextField();
        jEstadoAna = new javax.swing.JComboBox<>();
        jAcresAna = new javax.swing.JButton();
        jRemovAna = new javax.swing.JButton();
        jEditarAna = new javax.swing.JButton();
        jProcurarAna = new javax.swing.JButton();
        jSenhaAna = new javax.swing.JTextField();
        jBairroAna = new javax.swing.JTextField();
        jPermiAna = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

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

        jPasswordField1.setText("jPasswordField1");

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Sistema de chamados");
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ID*");
        jLabel1.setInheritsPopupMenu(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nome*");
        jLabel2.setInheritsPopupMenu(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("E-mail");
        jLabel3.setInheritsPopupMenu(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Endereço");
        jLabel4.setInheritsPopupMenu(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Bairro");
        jLabel5.setInheritsPopupMenu(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Cidade");
        jLabel8.setInheritsPopupMenu(false);

        jCidadeAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Telefone*");
        jLabel6.setInheritsPopupMenu(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Login*");
        jLabel11.setInheritsPopupMenu(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Senha*");
        jLabel12.setInheritsPopupMenu(false);

        jLoginAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Permissão*");
        jLabel13.setInheritsPopupMenu(false);

        jIDAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jIDAna.setToolTipText("Digite o número do analista e clique na lupa para pesquisar");

        jNomeAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jEmailAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jEndAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Estado");

        jTelefoneAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jEstadoAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEstadoAna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jEstadoAna.setSelectedIndex(20);

        jAcresAna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/adicionar.png"))); // NOI18N
        jAcresAna.setToolTipText("Adicionar");
        jAcresAna.setPreferredSize(new java.awt.Dimension(70, 70));
        jAcresAna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcresAnaActionPerformed(evt);
            }
        });

        jRemovAna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/remover.png"))); // NOI18N
        jRemovAna.setToolTipText("Remover");
        jRemovAna.setEnabled(false);
        jRemovAna.setPreferredSize(new java.awt.Dimension(70, 70));
        jRemovAna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemovAnaActionPerformed(evt);
            }
        });

        jEditarAna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/editar.png"))); // NOI18N
        jEditarAna.setToolTipText("Editar");
        jEditarAna.setEnabled(false);
        jEditarAna.setPreferredSize(new java.awt.Dimension(70, 70));
        jEditarAna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditarAnaActionPerformed(evt);
            }
        });

        jProcurarAna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/procurar.png"))); // NOI18N
        jProcurarAna.setToolTipText("Pesquisar analista");
        jProcurarAna.setPreferredSize(new java.awt.Dimension(70, 70));
        jProcurarAna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProcurarAnaActionPerformed(evt);
            }
        });

        jSenhaAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jBairroAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPermiAna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPermiAna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));
        jPermiAna.setSelectedIndex(1);

        jLabel9.setText("* Campo obrigatório");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jEmailAna, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jNomeAna, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jEndAna)
                                                .addComponent(jCidadeAna, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jBairroAna, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jEstadoAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jIDAna, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(144, 144, 144))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jAcresAna, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jRemovAna, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jEditarAna, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jProcurarAna, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTelefoneAna, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSenhaAna, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLoginAna, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPermiAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(236, 236, 236))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jAcresAna, jEditarAna, jProcurarAna, jRemovAna});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(134, 134, 134))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRemovAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jIDAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jNomeAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jEmailAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jEndAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jBairroAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jCidadeAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jEstadoAna, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTelefoneAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLoginAna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSenhaAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPermiAna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addComponent(jAcresAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jEditarAna, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProcurarAna, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jAcresAna, jEditarAna, jProcurarAna, jRemovAna});

        setBounds(0, 0, 803, 695);
    }// </editor-fold>//GEN-END:initComponents

//Metodo consultar analistas
    private void jProcurarAnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProcurarAnaActionPerformed
        procurarAna();
    }//GEN-LAST:event_jProcurarAnaActionPerformed

//Metodo acrescentar analistas
    private void jAcresAnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcresAnaActionPerformed
        acrescentar();
    }//GEN-LAST:event_jAcresAnaActionPerformed

//Metodo chamar edição analistas    
    private void jEditarAnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditarAnaActionPerformed
        editar();
    }//GEN-LAST:event_jEditarAnaActionPerformed

//Metodo chamar remoção de analistas
    private void jRemovAnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemovAnaActionPerformed
        excluir();
    }//GEN-LAST:event_jRemovAnaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAcresAna;
    private javax.swing.JTextField jBairroAna;
    private javax.swing.JTextField jCidadeAna;
    private javax.swing.JButton jEditarAna;
    private javax.swing.JTextField jEmailAna;
    private javax.swing.JTextField jEndAna;
    private javax.swing.JComboBox<String> jEstadoAna;
    private javax.swing.JTextField jIDAna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLoginAna;
    private javax.swing.JTextField jNomeAna;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JComboBox<String> jPermiAna;
    private javax.swing.JButton jProcurarAna;
    private javax.swing.JButton jRemovAna;
    private javax.swing.JTextField jSenhaAna;
    private javax.swing.JTextField jTelefoneAna;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
