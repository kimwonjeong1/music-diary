from django.http import HttpResponse
import json
 
from .MongoDbManager import MongoDbManager
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def specific_user( request, username):
    def get():
        dbUserData = MongoDbManager().get_users_from_collection( {'name': username})
        responseData = dbUserData[0]
        del responseData['_id']
 
        return HttpResponse( json.dumps( responseData), status=200)
 
    def post():
        try:
            age, job = request.POST['age'], request.POST['job']
        except:
            return HttpResponse( status=400)
 
        userData = {
            'name': username,
            'age': age,
            'job' : job
        }
 
        result = MongoDbManager().add_user_on_collection( userData)
        return HttpResponse( status=201) if result else HttpResponse( status=500)
 
    if request.method == 'GET':
        return get()
    elif request.method == 'POST':
        return post()
    else:
        return HttpResponse( status=405)
 

@csrf_exempt
def all_users( request):
    def get():
        dbUserData = MongoDbManager().get_users_from_collection({})
        responseData = []
        for user in dbUserData:
            del user['_id']
            responseData.append( user)
 
        return HttpResponse( json.dumps( responseData), status=200)
 
    if request.method == 'GET':
        return get()
    else:
        return HttpResponse( status=405)
