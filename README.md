게이트웨이 구현

1. 데이터 패치 (fetch) : RDB 지원 (jdbc driver 런타임 동적 로드)
2. 필터 (filter) :<br/>
   trim (좌우 공백 제거),<br/>
   column_append (컬럼 조합),<br/>
   column_split (컬럼 분리),<br/>
   html_strip (html 태그 제거),<br/>
   date_format (날짜 포매팅),<br/>
   field_case (대소문자 변환),<br/>
   custom (groovy script 작성 커스텀 필터)
3. 덤프 생성 (dump) : JSON Array 형태로 파일 write





