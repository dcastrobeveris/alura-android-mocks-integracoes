package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuario_QuandoSalvarUsuario(){
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario (
                dao,
                adapter,
                recyclerView);
        Usuario diego = new Usuario("Diego");
        when(dao.salva(diego)).thenReturn(new Usuario(1, "Diego"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(diego);

        verify(dao).salva(new Usuario("Diego"));
        verify(adapter).adiciona(new Usuario(1, "Diego"));
        verify(recyclerView).smoothScrollToPosition(0);
    }

}