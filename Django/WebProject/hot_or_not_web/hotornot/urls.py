from django.conf.urls import patterns, url
from hotornot import views

urlpatterns = patterns('',
        url(r'^statistics/$', views.base, name='base'),        
        url(r'^about/$', views.about, name='about'),        
        url(r'^top5$', views.top_five, name='about'),
        )