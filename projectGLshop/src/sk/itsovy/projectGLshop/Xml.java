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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
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

                Element item = doc.createElement("Item");
                rootItems.appendChild(item);

                Element name = doc.createElement("Name");
                item.appendChild(name);

                Element price = doc.createElement("Price");
                item.appendChild(price);

                Element amount = doc.createElement("Amount");

                if (item instanceof DrafInterface) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((DrafInterface) item).getVolume())));
                    item.appendChild(amount);
                } else if (item instanceof Fruit) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) item).getWeight())));
                    item.appendChild(amount);
                } else if (item instanceof Pc) {
                    amount.appendChild(doc.createTextNode(String.valueOf(((Pc) item).getAmount())));
                    item.appendChild(amount);
                }

                Element unit = doc.createElement("Unit");

                if (item instanceof DrafInterface) {
                    unit.appendChild(doc.createTextNode("l"));
                    item.appendChild(unit);
                } else if (item instanceof Fruit) {
                    unit.appendChild(doc.createTextNode("Kg"));
                    item.appendChild(unit);
                } else if (item instanceof Pc) {
                    unit.appendChild(doc.createTextNode("Pcs"));
                    item.appendChild(unit);
                }

            }

            Element totalPrice = doc.createElement("PriceOverall");
            totalPrice.appendChild(rootItems);

            Element eur = doc.createElement("EUR");
            eur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
            totalPrice.appendChild(eur);

            Element usd = doc.createElement("USD");
            usd.appendChild(doc.createTextNode(net.getFinalToUSD(bill.getFinalPrice())));
            totalPrice.appendChild(usd);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("bill.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("File saved!");
    }

}
