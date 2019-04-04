package sk.itsovy.projectGLshop;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.itsovy.projectGLshop.bill.Bill;
import sk.itsovy.projectGLshop.interfaces.DrafInterface;
import sk.itsovy.projectGLshop.interfaces.Pc;
import sk.itsovy.projectGLshop.items.Fruit;
import sk.itsovy.projectGLshop.items.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;

public class Xml {

    public void createXML(Bill bill) {
        Internet net = new Internet();

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootItems = doc.createElement("Items");
            doc.appendChild(rootItems);

            Date date = new java.sql.Date(bill.getDate().getTime());
            Date time = new java.sql.Time(bill.getDate().getTime());

            Element dateAll = doc.createElement("PurchaseDate");
            rootItems.appendChild(dateAll);

            Element date2 = doc.createElement("Date");
            date2.appendChild(doc.createTextNode(String.valueOf(date)));
            dateAll.appendChild(date2);

            Element time2 = doc.createElement("Time");
            time2.appendChild(doc.createTextNode(String.valueOf(time)));
            dateAll.appendChild(time2);

            for (Item billitem : bill.getList()) {

                Element mainItem = doc.createElement("Item");
                rootItems.appendChild(mainItem);

                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(billitem.getName()));
                mainItem.appendChild(name);

                Element price = doc.createElement("Price");
                price.appendChild(doc.createTextNode(String.valueOf(billitem.getPrice())));
                mainItem.appendChild(price);

                Element amount = doc.createElement("Amount");

                if (billitem instanceof DrafInterface) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((DrafInterface) billitem).getVolume())));
                    mainItem.appendChild(amount);
                } else if (billitem instanceof Fruit) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) billitem).getWeight())));
                    mainItem.appendChild(amount);
                } else if (billitem instanceof Pc) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((Pc) billitem).getAmount())));
                    mainItem.appendChild(amount);
                }

                Element unit = doc.createElement("Unit");

                if (billitem instanceof DrafInterface) {
                    unit.appendChild(doc.createTextNode("l"));
                    mainItem.appendChild(unit);
                } else if (billitem instanceof Fruit) {
                    unit.appendChild(doc.createTextNode("Kg"));
                    mainItem.appendChild(unit);
                } else if (billitem instanceof Pc) {
                    unit.appendChild(doc.createTextNode("Pcs"));
                    mainItem.appendChild(unit);
                }

            }


            Element totalprice = doc.createElement("TotalPrice");
            rootItems.appendChild(totalprice);

            Element priceEur = doc.createElement("EUR");
            priceEur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
            totalprice.appendChild(priceEur);

            Element priceDol = doc.createElement("USD");
            priceDol.appendChild(doc.createTextNode(net.getFinalToUSD(bill.getFinalPrice())));
            totalprice.appendChild(priceDol);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("bill.xml"));
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

}
