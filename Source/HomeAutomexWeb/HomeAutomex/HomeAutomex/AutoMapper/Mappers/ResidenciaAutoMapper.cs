using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class ResidenciaAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<Residencia, ResidenciaModel>();
            Mapper.CreateMap<ResidenciaModel, Residencia>();
            base.Configure();
        }
    }
}