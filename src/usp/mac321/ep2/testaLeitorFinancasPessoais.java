package usp.mac321.ep2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestaLeitorFinancasPessoais {

	LeitorFinancasPessoais lfp;
	
	@BeforeEach
	void setUp() throws Exception {
		lfp = new LeitorFinancasPessoais();
	}

	

	@Test
	void test() {
		List <Usuario> usuarios = lfp.leUsuarios("\"C:\\Users\\lclor\\Documents\\BackupPc\\Arquivos\\Estudos\\Eng.Comp\\MAC0321\\EP2-NUSP1-NUSP2\\csv\\usuarios.csv\"");
		assertEquals("Pai",usuarios.get(0).getApelido());
		//fail("Not yet implemented");
	}
	
	

}
