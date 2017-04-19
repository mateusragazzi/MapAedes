package com.example.mateus.mapaedes.Adapters;

/**
 * Created by zazah on 14/02/2017.
 */

public class BancoDeDadosAdapter {

    private int id_usuario;
    private String usuario;
    private String senha;
    private String cidade;
    private Double lat;
    private Double lng;
    private int  tipo;

    //Adapter


    private String nomeUser;
    private String userUser;
    private String senhaUser;
    private String cidadeUser;
    private int tipoUser;
    private Double latUser;
    private Double lngUser;


    private int id_usuarioDoenca;
    private String usuarioDoenca;
    private String enderecoDoenca;
    private String dataDoenca;
    private Double latDoenca;
    private Double lngDoenca;
    private String nomePessoaDoenca;
    private String tipoDoenca;

    public void setId_usuarioDoenca(int id_usuarioDoenca) {
        this.id_usuarioDoenca = id_usuarioDoenca;
    }

    public String getTipoDoenca() {
        return tipoDoenca;
    }

    public String getNomePessoaDoenca() {

        return nomePessoaDoenca;
    }

    public void setNomePessoaDoenca(String nomePessoaDoenca) {
        this.nomePessoaDoenca = nomePessoaDoenca;
    }

    public void setTipoDoenca(String tipoDoenca) {
        this.tipoDoenca = tipoDoenca;
    }
    public void setUsuarioDoenca(String usuarioDoenca) {
        this.usuarioDoenca = usuarioDoenca;
    }

    public void setEnderecoDoenca(String enderecoDoenca) {
        this.enderecoDoenca = enderecoDoenca;
    }

    public void setDataDoenca(String dataDoenca) {
        this.dataDoenca = dataDoenca;
    }

    public void setLatDoenca(Double latDoenca) {
        this.latDoenca = latDoenca;
    }

    public void setLngDoenca(Double lngDoenca) {
        this.lngDoenca = lngDoenca;
    }

    public int getId_usuarioDoenca() {
        return id_usuarioDoenca;
    }

    public String getUsuarioDoenca() {
        return usuarioDoenca;
    }

    public String getEnderecoDoenca() {
        return enderecoDoenca;
    }

    public String getDataDoenca() {
        return dataDoenca;
    }

    public Double getLatDoenca() {
        return latDoenca;
    }

    public Double getLngDoenca() {
        return lngDoenca;
    }



    //adapter
    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getUserUser() {
        return userUser;
    }

    public void setUserUser(String userUser) {
        this.userUser = userUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public String getCidadeUser() {
        return cidadeUser;
    }

    public void setCidadeUser(String cidadeUser) {
        this.cidadeUser = cidadeUser;
    }

    public int getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(int tipoUser) {
        this.tipoUser = tipoUser;
    }

    public Double getLatUser() {
        return latUser;
    }

    public void setLatUser(Double latUser) {
        this.latUser = latUser;
    }

    public Double getLngUser() {
        return lngUser;
    }

    public void setLngUser(Double lngUser) {
        this.lngUser = lngUser;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getCidade() {
        return cidade;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
