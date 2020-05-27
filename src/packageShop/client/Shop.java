package packageShop.client;

import packageShop.shared.FieldVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
//import com.example.tabela.client.Cw4_2.Contact;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.builder.shared.TitleBuilder;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabBar.Tab;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import javafx.css.ParsedValue;
import javafx.scene.control.ComboBox;

public class Shop implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
    final int bodyWeight = 1080;
    final int mainWeight = (int) (bodyWeight * 0.20) - 3;
    final int contentWeight = (int) (bodyWeight * 0.55) - 2;
    final int operationfieldWeight = (int) (bodyWeight * 0.25) -3 ;
    
    private static List<Kategoria> KATEGORIE = new ArrayList<Kategoria>();    
	private static List<Data> DATA = new ArrayList<Data>();
	private static List<Data> BUFOR = new ArrayList<Data>();
	private static List<Data> listElemantsToDelete = new ArrayList<Data>();
	private List<String> ListOfElement = new ArrayList<String>();
			
	private static boolean Akcja = false;
	
	CellTable<Data> table;
	
	final Label labelID = new Label();
	final Label labelnazwa = new Label();
	final Label labelopis = new Label();
	final Label labelcena = new Label();
	final Label labelsztuki = new Label();
	final Label labelkategoria = new Label();
	
	final TextBox texBoxID = new TextBox();
	final TextBox texBoxNazwa = new TextBox();
	final TextBox texBoxOpis = new TextBox();	
	final TextBox texBoxCena = new TextBox();	
	final TextBox texBoxSztuki = new TextBox();
	final TextBox texBoxKategoria = new TextBox();
	
	final DialogBox dialogBox = new DialogBox();
	ListBox listBox = new ListBox();
	
	public void onModuleLoad() {
		
		DATA.add(new Data(0, "Drukarka pentagon ", "Same kwadraty", (float) 519.99, 3, 31));
		DATA.add(new Data(1, "Drukarka Hb ", "Drukuje dobrze", (float) 334.99, 3, 32));
		DATA.add(new Data(2, "SPC Fortuna", "Ch³odzi jak Syberia", (float) 19.99, 2, 21));
		DATA.add(new Data(3, "Myszka", "Myszka ale nie Miki", (float) 119.99, 1, 11));
		DATA.add(new Data(4, "Myszka-ergo", "Myszka bardzo wygodna", (float) 249.99, 1, 11));
	
		KATEGORIE.add(new Kategoria(0, "Wszystko", null));
		KATEGORIE.add(new Kategoria(1, "Peryferia" ,null));
		KATEGORIE.add(new Kategoria(2, "Chlodzenia" ,null));
		KATEGORIE.add(new Kategoria(3, "Drukarki" ,null));
		KATEGORIE.add(new Kategoria(11, "Myszki" ,null));
		KATEGORIE.add(new Kategoria(12, "Klawiatury" ,null));
		KATEGORIE.add(new Kategoria(13, "Gamepady" ,null));
		KATEGORIE.add(new Kategoria(21, "Chlodzenia powietrzne" ,null));
		KATEGORIE.add(new Kategoria(22, "Ciekly Azot" ,null));
		KATEGORIE.add(new Kategoria(31, "Laserowa" ,null));
		KATEGORIE.add(new Kategoria(32, "Atramentowa", null));
		
		KATEGORIE.get(0).addPotkategia(KATEGORIE.get(1).IdKategori);
		KATEGORIE.get(0).addPotkategia(KATEGORIE.get(2).IdKategori);
		KATEGORIE.get(0).addPotkategia(KATEGORIE.get(3).IdKategori);
		KATEGORIE.get(1).addPotkategia(KATEGORIE.get(4).IdKategori);
		KATEGORIE.get(1).addPotkategia(KATEGORIE.get(5).IdKategori);
		KATEGORIE.get(1).addPotkategia(KATEGORIE.get(6).IdKategori);
		KATEGORIE.get(2).addPotkategia(KATEGORIE.get(7).IdKategori);
		KATEGORIE.get(2).addPotkategia(KATEGORIE.get(8).IdKategori);
		KATEGORIE.get(3).addPotkategia(KATEGORIE.get(9).IdKategori);
		KATEGORIE.get(3).addPotkategia(KATEGORIE.get(10).IdKategori);
		
// ====================================== HEADER ======================================
// ====================================== HEADER ======================================
// ====================================== HEADER ======================================	
		
		VerticalPanel col1 = new VerticalPanel();
		col1.setWidth(Integer.toString(mainWeight)+"px");
		
		VerticalPanel col2 = new VerticalPanel();
		col2.setWidth(Integer.toString(contentWeight)+"px");
		
		VerticalPanel col3 = new VerticalPanel();
		col3.setWidth(Integer.toString(operationfieldWeight)+"px");	
		
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.add(col1);
		mainPanel.add(col2);
		mainPanel.add(col3);
		mainPanel.setBorderWidth(1);
		mainPanel.addStyleName("subbody");
	RootPanel.get("subbody").add(mainPanel);
		
		final Label mainTitle = new Label();
		mainTitle.setText("KOM-EX");
		mainTitle.addStyleName("h1");
		
		final Label subTitle = new Label();
		subTitle.setText("Asystent dodawania towaru ...");
		subTitle.addStyleName("h2");
		
		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.add(mainTitle);
		titlePanel.add(subTitle);
		titlePanel.addStyleName("header");;
	RootPanel.get("title").add(titlePanel);
		
// ====================================== CONTENT ======================================
// ====================================== CONTENT ======================================
// ====================================== CONTENT ======================================

		
		
		TreeItem shopAssortmentTree = new TreeItem();
		shopAssortmentTree.setText("Asortyment");
		
		TreeItem item;
		for(int i = 1; i < 4; i++) {
			item = new TreeItem();
			item.setText(KATEGORIE.get(i).getNazwa());
			shopAssortmentTree.addItem(item);
			
			for (int id: KATEGORIE.get(i).getPotkategia()) {
				TreeItem subItem = new TreeItem();
				String nazwa = "";
				for (Kategoria k  : KATEGORIE) {
					if(k.IdKategori != id) continue;
					nazwa = k.nazwa;
					break;
				}
				subItem.setText(nazwa);
				item.addItem(subItem);
			}
		};

		final Label labelMessageCount = new Label();
		labelMessageCount.setAutoHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
		final Label labelMessage = new Label();
		labelMessage.setAutoHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
		Tree tree = new Tree();
		tree.addItem(shopAssortmentTree);
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				String nazwaKategori = event.getSelectedItem().getText();
				labelMessage.setText("Wyszukano "+ nazwaKategori);
				
				int idKategori = 0;
				for (Kategoria kategoria: KATEGORIE) {
					if(kategoria.getNazwa() != nazwaKategori) continue;
					idKategori = kategoria.getIdKategori();
					break;
				}
				
				BUFOR.clear();

				DisplayDataWithCategory(idKategori);
				
				if(BUFOR.size() < 1) 	labelMessageCount.setText("Brak asortymentu do wyswietlenia !");
				else 					labelMessageCount.setText("");
												
				table.setRowCount(BUFOR.size(), false); 
				table.setRowData(0, BUFOR);
			}
		});
						
		col1.setSpacing(10);
		col1.add(tree);
		col1.add(labelMessage);
				
// ====================================== ====================================== ======================================
// ====================================== ===============LISTA================== ======================================
// ====================================== ==v==V=========================V==v=== ======================================
		
		 table = new CellTable<Data>();
		 table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		 table.setWidth(Integer.toString(operationfieldWeight)+"px");
		 
		 final ListDataProvider<Data> dataProvider = new ListDataProvider<Data>();
		 dataProvider.addDataDisplay(table);
		 
		   BUFOR = dataProvider.getList();
		    //for (Data contact : DATA) {
		    //    BUFOR.add(contact);
		   // }
		 
		 TextColumn<Data> nazwaColumn = new TextColumn<Data>() {
			 @Override
			 public String getValue(Data object) { return object.nazwa; }
		 };	 
		 TextColumn<Data> cenaColumn = new TextColumn<Data>() {
			 @Override
			 public String getValue(Data object) { return Float.toString(object.cena); }
		 };
		 TextColumn<Data> sztukiColumn = new TextColumn<Data>() {
			 @Override
			 public String getValue(Data object) { return Integer.toString(object.getSztuki()); }
		 };
		 TextColumn<Data> kategoriaColumn = new TextColumn<Data>() {
			 @Override
			 public String getValue(Data object) { return Integer.toString(object.getKategoria()); }
		 };
		 
		 table.setWidth("100%", true);
		 
		 table.addColumn(nazwaColumn, "Nazwa");
		 table.addColumn(cenaColumn, "Cena");
		 table.addColumn(sztukiColumn, "Sztuki");
		 table.addColumn(kategoriaColumn, "Kategoria");

		 table.setColumnWidth(nazwaColumn, 35.0, Unit.PCT);
		 table.setColumnWidth(cenaColumn, 35.0, Unit.PCT);
		 table.setColumnWidth(sztukiColumn, 15.0, Unit.PCT);
		 table.setColumnWidth(kategoriaColumn, 15.0, Unit.PCT);
		 
		 // Dodanie modelu wyboru do obs³ugi wyboru u¿ytkownika.
		 final SingleSelectionModel<Data> selectionModel = new SingleSelectionModel<Data>();
		 table.setSelectionModel(selectionModel);
		 
		 selectionModel.addSelectionChangeHandler(
			 new SelectionChangeEvent.Handler() {
				 public void onSelectionChange(SelectionChangeEvent event) {
					 Data selected = selectionModel.getSelectedObject();
					 if (selected != null) FillForm(selected);;
				 }
			 });

		 RefreshDataTable(table);

		 col2.add(table);
		 col2.add(labelMessageCount);
		
// ====================================== ====================================== ======================================
// ====================================== ====================================== ======================================
// ====================================== ====================================== ======================================
		    
		final Label operationTitle = new Label();
		operationTitle.setText("operacje");
		operationTitle.addStyleName("h3");
		
		labelID.setText("ID:");
		labelnazwa.setText("Nazwa:");
		labelopis.setText("Opis:");
		labelcena.setText("Cena:");
		labelsztuki.setText("Sztuki:");
		labelkategoria.setText("Kategoria:");	
				
		labelID.setStyleName("label");
		texBoxID.setStyleName("texBox");
		labelnazwa.setStyleName("label");
		texBoxNazwa.setStyleName("texBox");
		labelopis.setStyleName("label");
		texBoxOpis.setStyleName("texBox");
		labelcena.setStyleName("label");
		texBoxCena.setStyleName("texBox");
		labelsztuki.setStyleName("label");
		texBoxSztuki.setStyleName("texBox");
		labelkategoria.setStyleName("label");
		texBoxKategoria.setStyleName("texBox");
		
		
		List <Label> labelList = Arrays.asList(labelID,labelnazwa,labelopis,labelcena,labelsztuki,labelkategoria);
		for(Label label : labelList) label.setAutoHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
		List <TextBox> texBoxList = Arrays.asList(texBoxID,texBoxNazwa,texBoxOpis,texBoxCena,texBoxSztuki,texBoxKategoria);
		for(TextBox textBox : texBoxList) textBox.setWidth("100%");
				
		Button buttonDodaj = new Button("Dodaj Produkt");
		Button buttonWyszukaj = new Button("Wyszukaj Produkt");
		Button buttonUsun = new Button("Usun Produkt");
		
		texBoxKategoria.setTitle("11 - Myszki \r\n" + 
				"12 - Klawiatury \r\n" + 
				"13 - Gamepady \r\n" + 
				"21 - Chlodzenia powietrzne \r\n" + 
				"22 - Ciekly Azot \r\n" + 
				"31 - Laserowa \r\n" + 
				"32 - Atramentowa");
		
		listBox = onInitialize();
		
		col3.setSpacing(8);
		col3.add(operationTitle);
		col3.add(labelID);
		col3.add(texBoxID);
		col3.add(labelnazwa);
		col3.add(texBoxNazwa);
		col3.add(labelopis);
		col3.add(texBoxOpis);
		col3.add(labelcena);
		col3.add(texBoxCena);
		col3.add(labelsztuki);
		col3.add(texBoxSztuki);
		col3.add(labelkategoria);
		//col3.add(texBoxKategoria);
		col3.add(listBox);
	    col3.add(buttonDodaj);
	    col3.add(buttonWyszukaj);
	    col3.add(buttonUsun);

	    buttonDodaj.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		        
		        Data newData = GetDataToAddFromForm();
		        if(newData == null) return;
		        
		        AddToData(newData);
		        ClearForm();
		        
		        dataProvider.getList().clear();
		        DisplayDataWithCategory(newData.getKategoria());
		        dataProvider.refresh();
		    }
		});
	    
	    buttonWyszukaj.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Data DataToSearch = ParseDataFromForm();
				
				FindItem(DataToSearch);
				
				if(BUFOR.size() < 1) 	labelMessageCount.setText("Brak asortymentu do wyswietlenia !");
				else 					labelMessageCount.setText("");
			}
		});
	    
	    buttonUsun.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			Data DataToDelete = ParseDataFromForm();
	        
			DeleteItem(DataToDelete);
		    }
		});

// ====================================== FOTTER ======================================
// ====================================== FOTTER ======================================
// ====================================== FOTTER ======================================
		
		VerticalPanel footPanel = new VerticalPanel();
		
		final Label labalFooter1 = new Label();
		labalFooter1.setText("Wszelkie prawa zastrzezone ");
				
		final Label labalFooter2 = new Label();
		labalFooter2.setText("Wszystkie teksty, rysunki, zdjecia oraz wszystkie inne informacje opublikowane na niniejszych stronach podlegaja prawom autorskim firmy KOM-EX.\r\n"); 
		labalFooter2.setAutoHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
		final Label labalFooter3 = new Label();
		labalFooter3.setText("Wszelkie kopiowanie, dystrybucja, elektroniczne przetwarzanie oraz przesylanie zawartosci bez zezwolenia firmy KOM-EX jest zabronione");
		labalFooter3.setAutoHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		
		labalFooter1.addStyleName("h2");
		footPanel.add(labalFooter1);
		footPanel.add(labalFooter2);
		footPanel.add(labalFooter3);
		footPanel.addStyleName("footer");;
		RootPanel.get("footer").add(footPanel);
	}
// ====================================== ====================================== ======================================
// ====================================== ====================================== ======================================
// ====================================== ====================================== ======================================
	
	private void DisplayDataWithCategory(int ID)
	{
		for (Kategoria k : KATEGORIE) {
			if(k.IdKategori != ID) continue;
			if(k.potkategia.size() < 1 || k.potkategia == null) break;
			
			for(int id_k : k.potkategia){
				DisplayDataWithCategory(id_k);
			}
			break;
		}
		
		AddToBufor(ID);
	}
	
	private void AddToBufor(int ID)
	{
		for (Data d : DATA) {
			if(d.getKategoria() != ID) continue;
			BUFOR.add(d);
		}
	}
	
	private Data GetDataToAddFromForm()
	{
		Data newData = ParseDataFromForm();
		
		if(!ValidateDataToAdd(newData)) return null;
		else return newData;
	}

	private Data ParseDataFromForm()
	{
		Data newData;
		
        int IdProdoktu = -1;
        String nazwa = "";
        String opis = "";
        float cena = (float)(-1.01); 
        int sztuki = -1;
        int kategoria = -1;

        try {
        	if(texBoxID.getText() != "") IdProdoktu = Integer.parseInt(texBoxID.getText().toString());
	    	nazwa = texBoxNazwa.getText().toString();
	    	opis = texBoxOpis.getText().toString();
	    	if(texBoxCena.getText() != "") cena = Float.parseFloat(texBoxCena.getText().toString());
	    	if(texBoxSztuki.getText() != "") sztuki = Integer.parseInt(texBoxSztuki.getText().toString());
	    	//if(texBoxKategoria.getText() != "") kategoria = Integer.parseInt(texBoxKategoria.getText().toString());
	    	if(listBox.getSelectedItemText() != "")  kategoria = GetIdKategory(listBox.getSelectedItemText().toString());
	        newData = new Data(IdProdoktu,nazwa,opis,cena,sztuki,kategoria);
	        return newData;
        }
        catch(Exception e){
        	Window.alert("Podaj wszystkie dane !  Pamietaj o odpowiednim TYPIE danych Cena: [xx.xx] , kategoria[11 / 12 / 13 / 21 / 22 / 31 / 32]");
        }
        
		return null;
	}
	
	private boolean ValidateDataToAdd(Data data) {
		String comunicat = "";
		
		if(data.getNazwa().length() < 4) 	comunicat += "$  Nazwa musi miec co najmniej 4 litery  $";
		if(data.getCena() < (float)0.01) 	comunicat += "$  Cena musi byc wyzsza nie 0,00 PLN  $";
		if(data.getSztuki() < 1) 			comunicat += "$  Musisz dodac co najmniej jeda sztuke  $";
		if(data.getKategoria() < 4) 		comunicat += "$  Musiz dodac kategorie  $";
		
		if (comunicat.length() > 0) {
			Window.alert(comunicat);
			return false;
		}
		
		if(texBoxID.getText() == "") data.setIdProdoktu(Data.AUTO_INCREMENT());
		
	return true;
	}
	
	private void AddToData(Data data)	{
		DATA.add(data);
	}
	
	private void FindItem(Data data)
	{
		BUFOR.clear();
		BUFOR.addAll(SearchItem(data));
	}
	
	private void DeleteItem(Data data)	{
		listElemantsToDelete = SearchItem(data);
		BUFOR.clear();
		BUFOR.addAll(listElemantsToDelete);
		
		showCustomDialog(listElemantsToDelete.size());
	}
	
	private List<Data> SearchItem(Data data)	{
		
		List<Data> listFindedElement = new ArrayList<Data>();
		int index= -1;
		
		for (Data d : DATA) {
			index++;
			if(data.getIdProdoktu() != -1 	&& d.getIdProdoktu() != data.getIdProdoktu()) 	continue;
			if(data.getNazwa() != "" 		&& d.getNazwa() != data.getNazwa()) 			continue;
			if(data.getOpis() != "" 		&& d.getOpis() != data.getOpis()) 				continue;
			if(data.getCena() != -1.01 		&& d.getCena() != data.getCena()) 				continue;
			if(data.getSztuki() != -1 		&& d.getSztuki() != data.getSztuki()) 			continue;
			if(data.getKategoria() != -1 	&& d.getKategoria() != data.getKategoria()) 	continue;
			
			//DATA.remove(index);
			listFindedElement.add(d);
		}
		
		return listFindedElement;
	}
		
	private DialogBox showCustomDialog(int licznik) {

	   final DialogBox dialog = new DialogBox(false, true);
       dialog.setText("Usuwanie ...");
       dialog.setPopupPosition(100, 150);
       
       String liczba = Integer.toString(licznik);
       
		Label content = new Label("Czy chcesz usunac wybrane elementy ? Sztuk: " + liczba);
		
		if (dialog.isAutoHideEnabled())  {
		dialog.setWidget(content);
		} 
		else {
			VerticalPanel vPanel = new VerticalPanel();vPanel.setSpacing(2);
			vPanel.add(content);
			vPanel.add(new Label("\n"));
			vPanel.add(new Button("NIE", new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialog.hide();
				}
			}));
			vPanel.add(new Button("TAK", new ClickHandler() {
				public void onClick(ClickEvent event) {
					ClearForm();
					DeleteFromData();
		         	dialog.hide();
				}
			}));
		dialog.setWidget(vPanel);
		}
		dialog.show();
		return dialog;
	}
	
	private void DeleteFromData ()
	{
		for (Data data : listElemantsToDelete) DATA.remove(data);
		listElemantsToDelete.clear();
				
		DisplayDataWithCategory(0);
	}
	
	public ListBox onInitialize() {
		ListBox dropBox = new ListBox(false);
		
		for (int i = 4; i < KATEGORIE.size(); i++) {
			ListOfElement.add(KATEGORIE.get(i).getNazwa());
			dropBox.addItem(KATEGORIE.get(i).getNazwa());
		}
		
		dropBox.ensureDebugId("cwListBox-dropBox");
		dropBox.setWidth("100%");
		    
		return dropBox;
	}
	
	private void RefreshDataTable(CellTable<Data> table) {
		table.setRowCount(BUFOR.size(), true); 
		table.setRowData(0, BUFOR);
		table.setRowCount(DATA.size(), true); 
		table.setRowData(0, DATA);
		table.redraw();
	}
	
	private void FillForm(Data data)
	{
		texBoxID.setText(Integer.toString(data.getIdProdoktu()));
		texBoxNazwa.setText(data.getNazwa());
		texBoxOpis.setText(data.getOpis());
		texBoxCena.setText(Float.toString(data.getCena()));
		texBoxSztuki.setText(Integer.toString(data.getSztuki()));
		texBoxKategoria.setText(Integer.toString(data.getKategoria()));
		listBox.setSelectedIndex( getIndexOfStringKategory(data.getKategoria()));
	}
	
	private int getIndexOfStringKategory(int i)
	{
		int indexOfSelectedItem=(-1);
		
		String name = GetNameKategory(i);
		for (String s : ListOfElement) {
			indexOfSelectedItem++;
			if(s == name) break;
		}
		return indexOfSelectedItem;
	}
	
	private void ClearForm()
	{
		texBoxID.setText("");
		texBoxNazwa.setText("");
		texBoxOpis.setText("");
		texBoxCena.setText("");
		texBoxSztuki.setText("");
		texBoxKategoria.setText("");
		
	}
	
	private int GetIdKategory(String name)
	{
		for(Kategoria k : KATEGORIE) {
			if(k.getNazwa() != name) continue;
			return k.getIdKategori();
		}
		return 0;
	}
	
	private String GetNameKategory(int id)
	{
		for(Kategoria k : KATEGORIE) {
			if(k.getIdKategori() != id) continue;
			return k.getNazwa();
		}
		return "";
	}
	
}
