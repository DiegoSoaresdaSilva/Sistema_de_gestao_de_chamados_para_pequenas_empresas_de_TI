package sistemadechamados.formularios;

import sistemadechamados.dados.DbaJava; //comunicação com o banco
import java.sql.*; //biblioteca SQL
import javax.swing.JOptionPane; //tratamento exceção
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;////Biblioteca para pesquisa avançada de itens
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap; //Framework Jaspereport: aponta para a chave e não para o valor

/**
 * Método referente ao formulário de chamados
 *
 * @author Diego Soares da Silva
 */
public class FormChamados extends javax.swing.JInternalFrame {

    /**
     * Metodo formulario de chamados.
     */

    Connection con = null; //Modulo de conexão do SQL
    PreparedStatement pp = null; ////Preprara o banco de dados para receber os dados da tela de login
    ResultSet result = null; //Resultado da consulta ao banco   

    public FormChamados() {
        initComponents();
        con = DbaJava.ligacao(); //chamando o metodo conector
    }

//Pesquisar cliente na tela de chamados
    private void pesqCliCha() {
        String bd = "SELECT *FROM tclientes WHERE nomecliente like ?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jClienteCha.getText() + "%");
            result = pp.executeQuery();
            jFormularioCha.setModel(DbUtils.resultSetToTableModel(result));
        } catch (Exception b) {
            JOptionPane.showMessageDialog(null, b);
        }
    }

//vinculo nome com ID
    private void atualizacaoCliCha() {
        int atualiza = jFormularioCha.getSelectedRow();
        jIDCliCha.setText(jFormularioCha.getModel().getValueAt(atualiza, 0).toString());

    }

//Metodo criar chamado
    private void criarCha() {
        String bd = "INSERT INTO tchamado (status,chamado,descricao,resolucao,analista,valor,idclientes) values(?,?,?,?,?,?,?)";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jSituCha.getSelectedItem().toString());
            pp.setString(2, jTituloCha.getText());
            pp.setString(3, jDecriCha.getText());
            pp.setString(4, jResolCha.getText());
            pp.setString(5, jAnaCha.getText());
            pp.setString(6, jValorCha.getText().replace(",", ".")); //correção não aceitar valores com vírgula
            pp.setString(7, jIDCliCha.getText());

            if ((jIDCliCha.getText().isEmpty()) || (jTituloCha.getText().isEmpty()) || (jDecriCha.getText().isEmpty()) || (jSituCha.getSelectedItem().equals(" "))) {
                JOptionPane.showMessageDialog(null, "*Campo(s) obrigatório (s) não preenchido(s)!");
            } else {
                int atualiza = pp.executeUpdate();
                if (atualiza > 0) {
                    JOptionPane.showMessageDialog(null, "Chamado aberto com sucesso");

                    jIDCliCha.setText(null); //ID do cliente
                    jTituloCha.setText(null); //título do chamado
                    jDecriCha.setText(null); //descrição do chamado
                    jResolCha.setText(null); //resolução
                    jClienteCha.setText(null); //Nome do cliente
                    jSituCha.setSelectedItem(" "); //Status do chamado
                    jValorCha.setText("0");
                    //Limpeza formulário
                    ((DefaultTableModel) jFormularioCha.getModel()).setRowCount(0);

                    jAddCha.setEnabled(true); //habilitar o botão adicionar
                    jImpriCha.setEnabled(false); //mantém o botão imprimir habilitado
                    jProCha.setEnabled(true); //mantém o botão pesquisar habilitado
                }
            }

        } catch (Exception c) {
            JOptionPane.showMessageDialog(null, c);
        }
    }

//Metodo pesquisar chamado
    private void pesqCha() {
        //abrir caixa de diálogo para pesquisar o chamado
        String numeroChamado = JOptionPane.showInputDialog("Nº do chamado:");
        String bd = "SELECT *FROM tchamado WHERE idchamado =" + numeroChamado;
        try {
            pp = con.prepareStatement(bd);
            result = pp.executeQuery(); // caso tenha um resultado positivo, ele irá preencher o formulário
            if (result.next()) {
                jIDCha.setText(result.getString(1));
                jDataCha.setText(result.getString(2));
                jSituCha.setSelectedItem(result.getString(3));
                jTituloCha.setText(result.getString(4));
                jDecriCha.setText(result.getString(5));
                jResolCha.setText(result.getString(6));
                jAnaCha.setText(result.getString(7));
                jValorCha.setText(result.getString(8));
                jIDCliCha.setText(result.getString(9));

                jAddCha.setEnabled(false); //botão adicionar
                jClienteCha.setEnabled(false); //Pesquisar cliente
                jFormularioCha.setVisible(false);

                //Habilita os botões
                jEditaCha.setEnabled(true);
                jRemvCha.setEnabled(true);
                jImpriCha.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "O Chamado não existe no sistema");
            }

        } catch (java.sql.SQLSyntaxErrorException a) { // Tratamento caso digite uma letra, ao vez de um número
            JOptionPane.showMessageDialog(null, "Valor inválido");
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, f);

        }
    }

//Metodo editar chamado  
    private void editarChamado() {
        String bd = "UPDATE tchamado SET status=?,chamado=?,descricao=?,resolucao=?,analista=?,valor=? WHERE idchamado=?";
        try {
            pp = con.prepareStatement(bd);
            pp.setString(1, jSituCha.getSelectedItem().toString());
            pp.setString(2, jTituloCha.getText());
            pp.setString(3, jDecriCha.getText());
            pp.setString(4, jResolCha.getText());
            pp.setString(5, jAnaCha.getText());
            pp.setString(6, jValorCha.getText().replace(",", ".")); //correção não aceitar valores com vírgula
            pp.setString(7, jIDCha.getText());

            if ((jIDCliCha.getText().isEmpty()) || (jTituloCha.getText().isEmpty()) || (jDecriCha.getText().isEmpty()) || (jSituCha.getSelectedItem().equals(" "))) {
                JOptionPane.showMessageDialog(null, "*Campo(s) obrigatório (s) não preenchido(s)!");
            } else {
                int atualiza = pp.executeUpdate();
                if (atualiza > 0) {
                    JOptionPane.showMessageDialog(null, "Chamado alterado");

                    //Limpa campos
                    jIDCha.setText(null);
                    jDataCha.setText(null);
                    jClienteCha.setText(null);
                    jTituloCha.setText(null);
                    jDecriCha.setText(null);
                    jResolCha.setText(null);
                    jAnaCha.setText(null);
                    jValorCha.setText("0");
                    jIDCliCha.setText(null);
                    //Limpa o campo situação
                    jSituCha.setSelectedItem(" ");

                    jAddCha.setEnabled(true);
                    jProCha.setEnabled(true);
                    jImpriCha.setEnabled(false);
                    jRemvCha.setEnabled(false);
                    jClienteCha.setEnabled(true);
                    jFormularioCha.setVisible(true);

                }
            }
        } catch (Exception c) {
            JOptionPane.showMessageDialog(null, c);
        }
    }

//Metodo excluir chamado
    private void excluirCha() {
        int aceita = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o chamado?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            String bd = "DELETE FROM tchamado WHERE idchamado=?";
            try {
                pp = con.prepareStatement(bd);
                pp.setString(1, jIDCha.getText());
                int excluir = pp.executeUpdate();
                if (excluir > 0) {
                    JOptionPane.showMessageDialog(null, "Atenção, chamado removido");

                    //Limpa campos
                    jIDCha.setText(null);
                    jDataCha.setText(null);
                    jClienteCha.setText(null);
                    jTituloCha.setText(null);
                    jDecriCha.setText(null);
                    jResolCha.setText(null);
                    jAnaCha.setText(null);
                    jValorCha.setText("0");
                    jIDCliCha.setText(null);
                    //Limpa o campo situação
                    jSituCha.setSelectedItem(" ");

                    jAddCha.setEnabled(true);
                    jProCha.setEnabled(true);
                    jImpriCha.setEnabled(false);
                    //
                    jRemvCha.setEnabled(false);
                    jClienteCha.setEnabled(true);
                    jFormularioCha.setVisible(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

//Metodo imprimir chamado
    private void imprimirCha() {
        int aceita = JOptionPane.showConfirmDialog(null, "Gerar o relatório do chamado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (aceita == JOptionPane.YES_OPTION) {
            //Impressão de relatório com o Framework JasperReports    
            try {
                HashMap aponta = new HashMap();
                //Parset: converte caixa de texto em string
                aponta.put("idchamado", Integer.parseInt(jIDCha.getText()));

                //Classe JasperPrint: importada do JasperReport
                JasperPrint impressao = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/imprichamado.jasper"),aponta, con);

                //Exibe o relatório
                JasperViewer.viewReport(impressao, false);

            } catch (Exception v) {
                JOptionPane.showMessageDialog(null, v);

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
        jClienteCha = new javax.swing.JTextField();
        jIDCliCha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jFormularioCha = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jIDCha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDataCha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSituCha = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTituloCha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jImpriCha = new javax.swing.JButton();
        jAddCha = new javax.swing.JButton();
        jRemvCha = new javax.swing.JButton();
        jProCha = new javax.swing.JButton();
        jEditaCha = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jValorCha = new javax.swing.JTextField();
        jAnaCha = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jResolCha = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jDecriCha = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Sistema de chamados");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Pesquisar nome do Cliente*:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ID do cliente:");

        jClienteCha.setToolTipText("Procure aqui por clientes");
        jClienteCha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jClienteChaKeyReleased(evt);
            }
        });

        jIDCliCha.setEditable(false);
        jIDCliCha.setToolTipText("Campo automático");

        //
        jFormularioCha = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColIndex){
                return false;
            }
        };
        jFormularioCha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "E-mail", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jFormularioCha.setFocusable(false);
        jFormularioCha.getTableHeader().setReorderingAllowed(false);
        jFormularioCha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormularioChaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jFormularioCha);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº CHAMADO:");

        jIDCha.setEditable(false);
        jIDCha.setToolTipText("Campo automático");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("DATA:");

        jDataCha.setEditable(false);
        jDataCha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jDataCha.setToolTipText("Campo automático");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("SITUAÇÃO* :");

        jSituCha.setEditable(true);
        jSituCha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Fila", "Tarefa interna", "Em atendimento", "Concluído" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Título do chamado*:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Breve descrição do chamado*:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Resolução:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Analista:");

        jImpriCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/imprimir.png"))); // NOI18N
        jImpriCha.setToolTipText("Gerar relatório");
        jImpriCha.setEnabled(false);
        jImpriCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImpriChaActionPerformed(evt);
            }
        });

        jAddCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/adicionar.png"))); // NOI18N
        jAddCha.setToolTipText("Abrir novo chamado");
        jAddCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddChaActionPerformed(evt);
            }
        });

        jRemvCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/remover.png"))); // NOI18N
        jRemvCha.setToolTipText("Remover chamado");
        jRemvCha.setEnabled(false);
        jRemvCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemvChaActionPerformed(evt);
            }
        });

        jProCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/procurar.png"))); // NOI18N
        jProCha.setToolTipText("Clique na lupa para procurar o chamado");
        jProCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProChaActionPerformed(evt);
            }
        });

        jEditaCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemadechamados/formularios/editar.png"))); // NOI18N
        jEditaCha.setToolTipText("Editar chamado");
        jEditaCha.setEnabled(false);
        jEditaCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditaChaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Valor:");

        jValorCha.setText("0");
        jValorCha.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jResolCha.setColumns(20);
        jResolCha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jResolCha.setRows(5);
        jScrollPane2.setViewportView(jResolCha);

        jDecriCha.setColumns(20);
        jDecriCha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDecriCha.setRows(5);
        jDecriCha.setToolTipText(" ");
        jScrollPane3.setViewportView(jDecriCha);

        jLabel11.setText("*Campo Obrigatório");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jImpriCha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jAddCha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jRemvCha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(jEditaCha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jProCha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(214, 214, 214))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jValorCha, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jAnaCha)))
                                .addGap(530, 530, 530)))
                        .addGap(176, 176, 176))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSituCha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTituloCha, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jClienteCha, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jIDCliCha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jIDCha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jDataCha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jIDCliCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jIDCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDataCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jClienteCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSituCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jTituloCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jAnaCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jValorCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jEditaCha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jImpriCha)
                                .addComponent(jAddCha)
                                .addComponent(jRemvCha))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jProCha)))
                .addGap(245, 245, 245))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jAddCha, jEditaCha, jImpriCha, jRemvCha});

        jProCha.getAccessibleContext().setAccessibleDescription("");

        setBounds(0, 0, 987, 850);
    }// </editor-fold>//GEN-END:initComponents
//Chama metodo pesquisa de nome do cliente no chamado
    private void jClienteChaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jClienteChaKeyReleased
        pesqCliCha();
    }//GEN-LAST:event_jClienteChaKeyReleased

//Chama metodo vincula nome ao ID
    private void jFormularioChaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormularioChaMouseClicked
        atualizacaoCliCha();
    }//GEN-LAST:event_jFormularioChaMouseClicked

//Chama metodo dicionar chamado    
    private void jAddChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddChaActionPerformed
        criarCha();
    }//GEN-LAST:event_jAddChaActionPerformed

//Chama metodo pesquisar chamado    
    private void jProChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProChaActionPerformed
        pesqCha();
    }//GEN-LAST:event_jProChaActionPerformed

//Chama metodo editar chamadoo
    private void jEditaChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditaChaActionPerformed
        editarChamado();
    }//GEN-LAST:event_jEditaChaActionPerformed

//Chama metodo excluri chamado
    private void jRemvChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemvChaActionPerformed
        excluirCha();
    }//GEN-LAST:event_jRemvChaActionPerformed

//chama o metodo imprimir    
    private void jImpriChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImpriChaActionPerformed
        imprimirCha();
    }//GEN-LAST:event_jImpriChaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddCha;
    private javax.swing.JTextField jAnaCha;
    private javax.swing.JTextField jClienteCha;
    private javax.swing.JTextField jDataCha;
    private javax.swing.JTextArea jDecriCha;
    private javax.swing.JButton jEditaCha;
    private javax.swing.JTable jFormularioCha;
    private javax.swing.JTextField jIDCha;
    private javax.swing.JTextField jIDCliCha;
    private javax.swing.JButton jImpriCha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jProCha;
    private javax.swing.JButton jRemvCha;
    private javax.swing.JTextArea jResolCha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> jSituCha;
    private javax.swing.JTextField jTituloCha;
    private javax.swing.JTextField jValorCha;
    // End of variables declaration//GEN-END:variables
}
