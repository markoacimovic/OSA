package rs.ftn.osa.support;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import rs.ftn.osa.dto.SimpleQueryEsDTO;
import rs.ftn.osa.lucene.search.QueryBuilderCustom;

public class SearchQueryGenerator {

    /**
     * @return match query builder with parameters
     * */
    public static QueryBuilder createMatchQueryBuilder(SimpleQueryEsDTO simpleQueryEs) {
        if(simpleQueryEs.getValue().startsWith("\"") && simpleQueryEs.getValue().endsWith("\"")) {
            return QueryBuilderCustom.buildQuery(SearchType.PHRASE, simpleQueryEs.getField(), simpleQueryEs.getValue());
        } else {
            return QueryBuilderCustom.buildQuery(SearchType.FUZZY, simpleQueryEs.getField(), simpleQueryEs.getValue());
        }
    }

    /**
     * @return term level query builder with parameters
     * */
    public static QueryBuilder createTermLevelQueryBuilder(SimpleQueryEsDTO simpleQueryEs) {
        return QueryBuilderCustom.buildQuery(SearchType.TERM, simpleQueryEs.getField(), simpleQueryEs.getValue());
    }

    /**
     * @return range query builder with parameters
     * */
    public static QueryBuilder createRangeQueryBuilder(SimpleQueryEsDTO simpleQueryEs) {
        return QueryBuilderCustom.buildQuery(SearchType.RANGE, simpleQueryEs.getField(), simpleQueryEs.getValue());
    }

    /**
     * @param field - field from the index that is nested object
     * @param boolQueryBuilder - created query builder (should, must, must_not or something else)
     * @param scoreMode - score mode that is related with the nested query (None, Avg, Max, Total, Min)
     * @return nested query builder with parameters
     * */
    public static QueryBuilder createNestedQueryBuilder(String field, BoolQueryBuilder boolQueryBuilder, ScoreMode scoreMode) {
        return QueryBuilders.nestedQuery(field, boolQueryBuilder, scoreMode);
    }

    public static QueryBuilder createWordQuery(SimpleQueryEsDTO simpleQueryEsDTO){
        return QueryBuilderCustom.buildQuery(SearchType.MATCH, simpleQueryEsDTO.getField(), simpleQueryEsDTO.getValue());

    }
}
