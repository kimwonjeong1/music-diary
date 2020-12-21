from .models import Store
from rest_framework import serializers
from .models import User

class StoreSerializer(serializers.ModelSerializer):
    class Meta:
        model = Store
        fields = [
            "id",
            "store_name",
            "branch",
            "area",
            "tel",
            "address",
            "latitude",
            "longitude",
            "category_list",
        ]


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('_id','email','password','nickname')
    