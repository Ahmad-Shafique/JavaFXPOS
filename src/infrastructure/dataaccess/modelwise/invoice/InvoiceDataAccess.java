package infrastructure.dataaccess.modelwise.invoice;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import core.application.services.data.access.interfaces.modelwise.invoice.IInvoiceDataAccess;
import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import core.domain.model.entities.Category;
import core.domain.model.entities.Invoice;
import core.domain.model.entities._utilities.console;
import infrastructure.dataaccess._utilities.NewCategory;
import infrastructure.dataaccess._utilities.NewInvoice;
import infrastructure.dataaccess.base.NetworkBase;
import okhttp3.Response;

public class InvoiceDataAccess extends NetworkBase implements IInvoiceDataAccess {

    @Override
    public Object getById(int id) {
        String url = server + invoiceApi + "/" + id;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.isSuccessful() && response.code() == 200 ){
                String received = response.body().string();
                Invoice result = gson.fromJson(received, Invoice.class);
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean addToDatabase(Object obj) {
        try{
            Invoice rcv = (Invoice) obj;
            NewInvoice item = new NewInvoice(rcv.getUserId(), rcv.getSaleId(), rcv.getUserName(), rcv.getTotalPrice(),
                                            rcv.getPayable(), rcv.getPaid(), rcv.getReturned(), rcv.getTransactionTime(),
                                            rcv.getVat(), rcv.getDiscount());
            String json = gson.toJson(item);
            String url = server + invoiceApi;
            // console.log("item is: " + json);

            Response response = client.newCall(postRequestBuilder(url,json)).execute();

//            console.log("Invoice successfully created");

            return true;
        }catch (Exception e){
            console.log("Error in post request");
        }
        return false;
    }

    @Override
    public Object[] getAll() {

        String url = server + invoiceApi;
//        console.log("url: " + url);
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
//            console.log("response received");
            if(response.isSuccessful() && response.code() == 200 ){
//                console.log("reply is 200 ");
                String received = response.body().string();
//                console.log("response is: " + received);
                Invoice[] result = gson.fromJson(received, Invoice[].class);
//                console.log("Invoice objects created");
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }
}
