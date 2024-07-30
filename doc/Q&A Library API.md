# Q&A Library API

## Contributor

### contribute page

path: GET `/contribution`

response: html contribution page

### post contribution

path: POST `/contribution`

request: form data of Q&A

> Q&A form: question, answer, answer reference, difficulty, category

## Data format

1. difficulty: Enum (Student, Junior, Intermediate, Senior)
2. category: Enum (General, Backend, Frontend, Game, Infrastructure, data science)



