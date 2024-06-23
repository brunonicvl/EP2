package usp.mac321.ep2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestaLeitorFinancasPessoais {

	LeitorFinancasPessoais lfp;
	
	@BeforeEach
	void setUp() throws Exception {
		lfp = new LeitorFinancasPessoais();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		lfp.leUsuarios("\"C:\\Users\\lclor\\Documents\\BackupPc\\Arquivos\\Estudos\\Eng.Comp\\MAC0321\\EP2-NUSP1-NUSP2\\csv\\usuarios.csv\"");
		
		//fail("Not yet implemented");
	}
	
	

}
