package greenfield.group.com.fiascloudkladr.context;

import greenfield.group.com.fiascloudkladr.types.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс контекста поиска. Хранит основную информацию для запроса
 *
 * @author Ivanov Roman
 * @date 27.06.19
 * @since 8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContext {

    private final String url = "https://kladr-api.ru/api.php?token=rRHsBStEbA4hZeSHG97dG9rEk5FG8SYH";

    // Тип возвращаемых объектов (region, district, city, street, building)
    private ContentType contentType;

    // Количество возвращаемых объектов
    private int limit = 7;

    // Строка для поиска по названию
    private String query;

    // Если 1, сервис вернёт объекты вместе с родительскими
    // (для района это регион, для населённого пункта район и регион и т.п.)
    private String withParent = "0";

    private String cityId;

    private String streetId;

    public RequestContext(RequestContext context) {
        if (context != null) {
            this.contentType = context.getContentType();
            this.query = context.getQuery();
            this.cityId = context.getCityId();
            this.streetId = context.getStreetId();
        }
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public String getUrl() {
        return url;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getWithParent() {
        return withParent;
    }

    public void setWithParent(String withParent) {
        this.withParent = withParent;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * Метод возвращающий урл запроса со всеми параметрами
     *
     * @return урл с параметрами
     */
    public String requestBuild() {
        return url + (!query.isEmpty() ? "&query=" + query : "")
                + (contentType != null ? "&contentType=" + contentType : "")
                + (cityId != null ? "&cityId=" + cityId : "")
                + (streetId != null ? "&streetId=" + streetId : "")
                + "&limit=" + limit
                + "&withParent=" + withParent;
    }

    @Override
    public String toString() {
        return "RequestContext{" +
                "url='" + url + '\'' +
                ", contentType=" + contentType +
                ", limit=" + limit +
                ", query='" + query + '\'' +
                ", withParent='" + withParent + '\'' +
                ", cityId='" + cityId + '\'' +
                ", streetId='" + streetId + '\'' +
                '}';
    }
}
