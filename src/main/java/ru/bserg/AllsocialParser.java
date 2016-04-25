package ru.bserg;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.bserg.dao.Entity;
import ru.bserg.dao.ResponseObject;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by SBoichenko on 25.04.2016.
 */
public class AllsocialParser {

    // 45 17 87 107
    private static String CATEGORY = "783";

    private static String PATH = "D:\\tmp\\";

    private static List<Entity> entityList = new ArrayList<Entity>();

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Enter category number!");
        } else {
            CATEGORY = args[0];
        }

        System.out.println("START Parse cat: " + CATEGORY);

        /*
        http://allsocial.ru/entity
        ?category_id=107
        &offset=25
        &direction=1
        &is_closed=-1
        &is_verified=-1
        &list_type=3
        &order_by=diff_abs
        &period=day
        &platform=1
        &range=0:10000000
        &type_id=1
         */

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ;

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://allsocial.ru/entity");

        boolean end = false;
        int offset = 0;
        int all = 0;

        while (!end) {

            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("direction", "1")
                    .addParameter("is_closed", "-1")
                    .addParameter("is_verified", "-1")
                    .addParameter("list_type", "3")
                    .addParameter("order_by", "diff_abs")
                    .addParameter("period", "day")
                    .addParameter("platform", "1")
                    .addParameter("range", "0:10000000")
                    .addParameter("type_id", "1")
                    .addParameter("category_id", CATEGORY)
                    .addParameter("offset", String.valueOf(offset))
                    .build();

            httpGet.setURI(uri);

            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                //String sResponse = convertStreamToString(inputStream);
                //System.out.println(sResponse);
                ResponseObject object = objectMapper.readValue(inputStream, ResponseObject.class);
                all = object.getResponse().getTotal_count();

                System.out.println("CAT: " + CATEGORY + " ALL: " + all + " OFFSET: " + offset + " | " + object);

                offset += object.getResponse().getEntity().size();
                entityList.addAll(object.getResponse().getEntity());

            }

            if (offset >= all) {
                end = true;
            }
        }


        List<String> lines = entityList.stream().map(e -> {
            StringJoiner sj = new StringJoiner(";");
            sj
                    .add(e.getVk_id().toString())
                    .add(e.getCaption())
                    .add(e.getQuantity().toString())
                    .add(e.getVisitors().toString())
                    .add(e.getCpp().toString())
                    .add(e.getIs_closed().toString())
                    .add(e.getReach().toString());
            return sj.toString();
        }).collect(Collectors.toList());

        System.out.println("WELL DONE: " + lines.size() + " lines");
        lines.add(0, "VK ID; Название; Кол-во; Посетителей; CPP; Closed; Reach");

        Files.write(Paths.get(PATH + "\\Parse_" + CATEGORY + ".csv"), lines);


    }

}
