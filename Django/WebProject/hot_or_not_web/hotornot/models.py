# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#     * Rearrange models' order
#     * Make sure each model has one field with primary_key=True
# Feel free to rename the models, but don't rename db_table values or field names.
#
# Also note: You'll have to insert the output of 'django-admin.py sqlcustom [appname]'
# into your database.

from django.db import models

class Dayofweekhits(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    weekday = models.CharField(max_length=60, primary_key=True)
    hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'DayOfWeekHits'

class Dayofweekhits2(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    weekday = models.CharField(max_length=60, primary_key=True)
    hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'DayOfWeekHits2'
        
class Dayofweekhits3(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    weekday = models.CharField(max_length=60, primary_key=True)
    hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'DayOfWeekHits3'

class Venuecompleteinformation(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    latitude = models.FloatField(null=True, blank=True)
    longitude = models.FloatField(null=True, blank=True)
    city = models.CharField(max_length=150, blank=True)
    country = models.CharField(max_length=150, blank=True)
    class Meta:
        db_table = u'VenueCompleteInformation'

class Venuehits(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    total_hits = models.IntegerField(null=True, blank=True)
    celebrity_hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'VenueHits'

class Venuehits2(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    total_hits = models.IntegerField(null=True, blank=True)
    celebrity_hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'VenueHits2'
        
class Venuehits3(models.Model):
    name = models.CharField(max_length=255, primary_key=True)
    type = models.CharField(max_length=150, primary_key=True)
    total_hits = models.IntegerField(null=True, blank=True)
    celebrity_hits = models.IntegerField(null=True, blank=True)
    class Meta:
        db_table = u'VenueHits3'