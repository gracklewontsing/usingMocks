package mx.tec.lab.usingMocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UsingMocksApplicationTests {
	@Mock
	Map<String, String> translationMap;

	@Mock
	List<String> direwolvesList;

	@Mock
	List<String> swordsList;

	@Spy
	List<String> theWallCastlesList = new ArrayList<String>();

	@Captor
	ArgumentCaptor<String> swordName;

	@InjectMocks
	DothrakiTranslator translator = new DothrakiTranslator();

	@Test
	public void whenUseMockAnnotation_thenMockIsInjected(){
		direwolvesList.add("drogon");
		verify(direwolvesList).add(anyString());
		assertEquals(0,direwolvesList.size());

		when(direwolvesList.size()).thenReturn(100);
		assertEquals(100,direwolvesList.size());
	}

	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjected(){
		theWallCastlesList.add("Castle Black");
		theWallCastlesList.add("Eastwatch");

		verify(theWallCastlesList).add("Castle Black");
		verify(theWallCastlesList).add("Eastwatch");

		assertEquals(2, theWallCastlesList.size());

		doReturn(100).when(theWallCastlesList).size();
		assertEquals(100,theWallCastlesList.size());
	}

	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect(){
		when(translationMap.get("khaleesi")).thenReturn("queen");

		assertEquals("queen",translator.getTranslation("khaleesi"));
	}

	@Test
	public void whenUseCaptorAnnotation_thenTheSame(){
		swordsList.add("needle");
		verify(swordsList).add(swordName.capture());

		assertEquals("needle",swordName.getValue());
	}
}
