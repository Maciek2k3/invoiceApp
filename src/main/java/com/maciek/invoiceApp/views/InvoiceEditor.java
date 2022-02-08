package com.maciek.invoiceApp.views;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.service.InvoiceService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;


@SpringUI
public class InvoiceEditor extends UI {
    private VerticalLayout mainLayout;
    private NativeSelect<Long> client;
    TextField id = new TextField("ID");
    TextField invoiceNumber = new TextField("INVOICE NUMBER");
    DateField invoiceDate = new DateField("INVOICE DATE");
    final Grid<Invoice> grid;


    @Autowired
    private InvoiceService invoiceService;


    public InvoiceEditor(InvoiceService invoiceService) {
        this.grid = new Grid<>(Invoice.class);
    }

    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        uploadLayout();
        actions();
        find();
        setFindAll();
        list();


    }

    private void mainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label title = new Label("Invoice Service");
        header.addComponent(title);
        mainLayout.addComponents(header);
    }

    private void uploadLayout() {
        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setWidth(null);
        uploadLayout.setSpacing(true);
        client = new NativeSelect<>("CLIENT ID");

        mainLayout.addComponent(uploadLayout);
        uploadLayout.addComponents(id, invoiceNumber, invoiceDate, client);
        ArrayList<Long> clientId = new ArrayList<>();
        clientId.add(1L);
        clientId.add(2l);
        client.setItems(clientId);
        client.setValue(clientId.get(0));


    }

    private void actions() {
        HorizontalLayout actionLayout = new HorizontalLayout();
        actionLayout.setWidth(null);
        actionLayout.setSpacing(true);
        Button saveClient = new Button("Save");
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        actionLayout.addComponents(saveClient, delete, update);

        saveClient.addClickListener(clickEvent -> {
            if (!invoiceNumber.getValue().equals("")) {
                if (!invoiceDate.getValue().equals("")) {
                    saveData();
                    Notification.show("Added");
                } else {
                    Notification.show("Please enter data");
                }
            } else {
                Notification.show("Please enter data");
            }
        });
        delete.addClickListener(clickEvent -> {
            deleteItem();
            Notification.show("Deleted");
        });
        update.addClickListener(clickEvent -> {
            if (!invoiceNumber.getValue().equals("")) {
                saveData();
                Notification.show("Added");
            } else
                Notification.show("Please enter data");
        });
        mainLayout.addComponent(actionLayout);

    }

    private void find() {
        HorizontalLayout findLayout = new HorizontalLayout();
        Image imageItem = new Image();
        imageItem.setWidth("256px");
        imageItem.setHeight("256px");
        findLayout.setWidth(null);
        findLayout.setSpacing(true);
        Button findAll = new Button("FindAll");
        Button findById = new Button("FindByID");
        Button generatePdf = new Button("Generate PDF");
        generatePdf.addClickListener(e -> getUI().getPage().setLocation("/export"));
        findLayout.addComponents(findAll, findById, generatePdf);
        mainLayout.addComponent(findLayout);
        findAll.addClickListener(clickEvent -> list());
        findById.addClickListener(clickEvent -> {
            findOne();

        });

    }

    private void setFindAll() {
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.addComponent(grid);
        grid.setColumnOrder("id", "invoiceNumber", "invoiceDate");
        mainLayout.addComponent(gridLayout);

    }

    private void list() {
        grid.setItems(invoiceService.findAllInvoices());

    }

    private void saveData() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        Integer invoiceNum = Integer.parseInt(String.valueOf(invoiceNumber.getValue()));
        LocalDate locDat = invoiceDate.getValue();
        Client client1 = new Client(client.getValue(), "");

        invoiceService.saveInvoice(new Invoice(numId, invoiceNum, locDat, client1));

    }

    private void deleteItem() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        invoiceService.deleteInvoice(numId);
    }

    private void findOne() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        grid.setItems(invoiceService.findInvoiceById(numId).get());

    }

}




