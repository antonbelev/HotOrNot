from django.conf.urls import patterns, url
from hotornot import views

urlpatterns = patterns('',
        url(r'^$', views.index, name='index'))