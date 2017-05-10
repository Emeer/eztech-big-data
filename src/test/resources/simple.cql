CREATE TABLE IF NOT EXISTS customer (
id uuid PRIMARY KEY,
first_name text,
last_name text,
emails set<text>,
top_scores list<int>,
todo map<timestamp, text>
);

INSERT INTO customer(id, first_name, last_name, emails, top_scores) values(now(),'eric','zhou', {'emeerzhou@hotmail.com','emeerzhou@gmail.com'},[100, 200, 300]);

