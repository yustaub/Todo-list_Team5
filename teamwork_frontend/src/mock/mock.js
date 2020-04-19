import fetchMock from 'fetch-mock';


fetchMock.get('/api/tasks', [{"id":1,"content":"Restful API homework","updatedAt":"2020-04-05 00:00:00"}])
fetchMock.post('/api/tasks', [{}])
fetchMock.mock((/(|api|tasks)\/\d+/),[{}]);