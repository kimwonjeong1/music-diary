from django.conf.urls import url
from rest_framework.routers import DefaultRouter
from api import views
from .models import User
from django.urls import path
from api import HtmlTest, JsonTest

# router = DefaultRouter(trailing_slash=False)
# router.register(r"stores", views.StoreViewSet, basename="stores")
# #router.register('users',views.UserViewSet)
# urlpatterns = router.urls
urlpatterns = [
    path('json/', JsonTest.all_users, name='JsonAllUser'),
    path('json/<username>', JsonTest.specific_user, name='JsonSpecificUser'),
    path('html/', HtmlTest.all_users, name='HtmlAllUser'),
    path('html/<username>', HtmlTest.specific_user, name='HtmlSpecificUser')
]
